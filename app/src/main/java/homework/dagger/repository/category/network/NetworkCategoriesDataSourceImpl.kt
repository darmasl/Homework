package homework.dagger.repository.category.network

import homework.dagger.common.model.Category
import io.reactivex.Single

class NetworkCategoriesDataSourceImpl(
    private val service: CategoryService
) : NetworkCategoriesDataSource {
    override fun getCategories(): Single<List<Category>> {
        return service.getCategories()
            .map { response ->
                response.categories?.filterNotNull()?.map { mapFromNetwork(it) } ?: listOf()
            }
    }
}