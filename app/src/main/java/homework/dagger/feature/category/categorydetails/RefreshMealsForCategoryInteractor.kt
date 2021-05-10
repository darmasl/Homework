package homework.dagger.feature.category.categorydetails

import homework.dagger.common.interactor.SingleRxInteractor
import homework.dagger.common.model.Meal
import homework.dagger.repository.meal.MealsRepository
import io.reactivex.Single

class RefreshMealsForCategoryInteractor(
    private val mealsRepository: MealsRepository
) : SingleRxInteractor<MealsForCategoryParams, List<Meal>>() {
    override fun getSingle(params: MealsForCategoryParams): Single<List<Meal>> {
        return mealsRepository.getMealsForCategory(params.category, needRefresh = true)
    }
}