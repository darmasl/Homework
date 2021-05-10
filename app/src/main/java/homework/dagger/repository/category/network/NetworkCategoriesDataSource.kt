package homework.dagger.repository.category.network

import homework.dagger.common.model.Category
import io.reactivex.Single

interface NetworkCategoriesDataSource {
    fun getCategories(): Single<List<Category>>
}