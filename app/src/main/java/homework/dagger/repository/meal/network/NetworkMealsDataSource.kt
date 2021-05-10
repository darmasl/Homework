package homework.dagger.repository.meal.network

import homework.dagger.common.model.Category
import homework.dagger.common.model.Meal
import io.reactivex.Single

interface NetworkMealsDataSource {
    fun getMealsForCategory(category: Category): Single<List<Meal>>
}