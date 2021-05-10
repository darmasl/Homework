package homework.dagger.repository.category

import homework.dagger.common.model.Category
import io.reactivex.Single

interface CategoriesRepository {
    fun getCategories(needRefresh: Boolean = false): Single<List<Category>>
}