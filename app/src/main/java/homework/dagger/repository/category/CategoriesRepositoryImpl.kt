package homework.dagger.repository.category

import android.util.Log
import homework.dagger.common.model.Category
import homework.dagger.common.utils.PreferencesUtils
import homework.dagger.repository.category.db.LocalCategoriesDataSource
import homework.dagger.repository.category.network.NetworkCategoriesDataSource
import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val networkSource: NetworkCategoriesDataSource,
    private val localSource: LocalCategoriesDataSource,
    private val preferencesUtils: PreferencesUtils
) : CategoriesRepository {
    companion object {
        private val TAG = CategoriesRepositoryImpl::class.java.canonicalName
        private val THRESHOLD_EXPIRE = TimeUnit.DAYS.toMillis(1) //one day
        private const val KEY_CATEGORY_LAST_SYNC = "category_db_last_sync"
    }

    override fun getCategories(needRefresh: Boolean): Single<List<Category>> {
        return if (!needRefresh && checkCacheValid()) {
            getCategoriesFromDB()
        } else {
            getCategoriesFromNetwork()
        }
    }

    private fun getCategoriesFromDB(): Single<List<Category>> {
        Log.d(TAG, "Load categories from DB")
        return localSource.getCategories()
    }

    private fun getCategoriesFromNetwork(): Single<List<Category>> {
        Log.d(TAG, "Load categories from Network")
        return networkSource.getCategories()
            .doOnSuccess {
                localSource.storeCategories(it)
                preferencesUtils.setDate(KEY_CATEGORY_LAST_SYNC, Date())
            }
    }

    private fun checkCacheValid(): Boolean {
        val lastSync = preferencesUtils.getDate(KEY_CATEGORY_LAST_SYNC)
        return lastSync != null && (Date().time - lastSync.time) < THRESHOLD_EXPIRE
    }
}