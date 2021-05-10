package homework.dagger.repository.category.db

import homework.dagger.common.model.Category
import io.reactivex.Single

class LocalCategoriesDataSourceImpl(
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