package homework.dagger.repository.category.db

import homework.dagger.common.model.Category
import io.reactivex.Single
import javax.inject.Inject

class LocalCategoriesDataSourceImpl @Inject constructor(
    private val dao: CategoriesDao
) : LocalCategoriesDataSource {
    override fun storeCategories(categories: List<Category>) {
        dao.removeAllAndSetCategories(
            categories.map { mapToDB(it) }
        )
    }

    override fun getCategories(): Single<List<Category>> {
        return dao.getCategories()
            .map { categories ->
                categories.map { mapFromDB(it) }
            }
            .toSingle(listOf())
    }
}