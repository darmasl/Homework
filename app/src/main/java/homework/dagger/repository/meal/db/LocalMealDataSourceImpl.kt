package homework.dagger.repository.meal.db

import homework.dagger.common.model.Category
import homework.dagger.common.model.Meal
import io.reactivex.Single
import javax.inject.Inject

class LocalMealDataSourceImpl @Inject constructor(
    private val dao: MealsDao
) : LocalMealsDataSource {
    override fun getMealsForCategory(category: Category): Single<List<Meal>> {
        return dao.getMealsForCategory(category.strCategory ?: "")
            .map { it.map(::mapFromDB) }
    }

    override fun storeMealForCategory(meals: List<Meal>, category: Category) {
        dao.insertMeals(
            meals.map { mapToDB(it, category) }
        )
    }
}