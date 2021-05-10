package homework.dagger.repository.category.db

import homework.dagger.common.model.Category
import io.reactivex.Single

interface LocalCategoriesDataSource {
    fun getCategories(): Single<List<Category>>
    fun storeCategories(categories: List<Category>)
}