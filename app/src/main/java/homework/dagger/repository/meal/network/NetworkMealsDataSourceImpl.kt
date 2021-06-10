package homework.dagger.repository.meal.network

import homework.dagger.common.model.Category
import homework.dagger.common.model.Meal
import io.reactivex.Single
import javax.inject.Inject

class NetworkMealsDataSourceImpl @Inject constructor(
    private val service: MealsService
) : NetworkMealsDataSource {
    override fun getMealsForCategory(category: Category): Single<List<Meal>> {
        return service.getMealsByCategory(category.strCategory ?: "")
            .map { it.meals?.filterNotNull()?.map(::mapFromNetwork) ?: listOf() }
    }
}