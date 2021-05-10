package homework.dagger.repository.meal.db

import homework.dagger.common.model.Category
import homework.dagger.common.model.Meal
import io.reactivex.Single

interface LocalMealsDataSource {
    fun getMealsForCategory(category: Category): Single<List<Meal>>
    fun storeMealForCategory(meals: List<Meal>, category: Category)
}