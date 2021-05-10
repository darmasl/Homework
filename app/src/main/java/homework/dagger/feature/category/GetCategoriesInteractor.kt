package homework.dagger.feature.category

import homework.dagger.common.interactor.SingleRxInteractor
import homework.dagger.common.model.Category
import homework.dagger.repository.category.CategoriesRepository
import io.reactivex.Single

class GetCategoriesInteractor(
    private val categoriesRepository: CategoriesRepository
) : SingleRxInteractor<Unit, List<Category>>() {
    override fun getSingle(
        params: Unit
    ): Single<List<Category>> {
        return categoriesRepository.getCategories()
    }
}