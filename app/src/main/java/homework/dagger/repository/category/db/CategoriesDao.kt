package homework.dagger.repository.category.db

import androidx.room.*
import homework.dagger.repository.model.db.CategoryEntity
import io.reactivex.Maybe

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: List<CategoryEntity>)

    @Query("SELECT * from category")
    fun getCategories(): Maybe<List<CategoryEntity>>

    @Query("DELETE from category")
    fun deleteCategories()

    @Transaction
    fun removeAllAndSetCategories(categories: List<CategoryEntity>) {
        deleteCategories()
        insertCategories(categories)
    }
}