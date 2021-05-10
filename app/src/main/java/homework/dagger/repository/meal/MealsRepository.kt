package homework.dagger.repository.meal

import homework.dagger.common.model.Category
import homework.dagger.common.model.Meal
import io.reactivex.Single

interface MealsRepository {
    fun getMealsForCategory(category: Category, needRefresh: Boolean = false): Single<List<Meal>>
}