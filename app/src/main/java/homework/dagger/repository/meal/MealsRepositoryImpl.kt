package homework.dagger.repository.meal

import android.util.Log
import homework.dagger.common.model.Category
import homework.dagger.common.model.Meal
import homework.dagger.common.utils.PreferencesUtils
import homework.dagger.repository.meal.db.LocalMealsDataSource
import homework.dagger.repository.meal.network.NetworkMealsDataSource
import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val localSource: LocalMealsDataSource,
    private val networkSource: NetworkMealsDataSource,
    private val preferencesUtils: PreferencesUtils
) : MealsRepository {
    companion object {
        private val TAG = MealsRepositoryImpl::class.java.canonicalName
        private val THRESHOLD_EXPIRE = TimeUnit.DAYS.toMillis(1) //one day
        private const val KEY_MEAL_CATEGORY_LAST_SYNC_PATTERN = "meal_for_category_%s_db_last_sync"
    }

    override fun getMealsForCategory(category: Category, needRefresh: Boolean): Single<List<Meal>> {
        return if (!needRefresh && checkCacheValid(category)) {
            getMealsForCategoryFromDB(category)
        } else {
            getMealsForCategoryFromNetwork(category)
        }
    }

    private fun getMealsForCategoryFromNetwork(category: Category): Single<List<Meal>> {
        Log.d(TAG, "Load meals for category ${category.strCategory} from Network")
        return networkSource.getMealsForCategory(category)
            .doOnSuccess {
                localSource.storeMealForCategory(it, category)
                preferencesUtils.setDate(
                    key = String.format(KEY_MEAL_CATEGORY_LAST_SYNC_PATTERN, category.idCategory),
                    date = Date()
                )
            }
    }

    private fun getMealsForCategoryFromDB(category: Category): Single<List<Meal>> {
        Log.d(TAG, "Load meals for category ${category.strCategory} from DB")
        return localSource.getMealsForCategory(category)
    }

    private fun checkCacheValid(category: Category): Boolean {
        val lastSync =
            preferencesUtils.getDate(KEY_MEAL_CATEGORY_LAST_SYNC_PATTERN.format(category.idCategory))
        return lastSync != null && (Date().time - lastSync.time) < THRESHOLD_EXPIRE
    }
}