package homework.dagger.repository.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "category",
    indices = [
        Index("str_category", unique = true)
    ]
)
class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_category")
    val idCategory: String,
    @ColumnInfo(name = "str_category")
    val strCategory: String? = null,
    @ColumnInfo(name = "str_category_description")
    val strCategoryDescription: String? = null,
    @ColumnInfo(name = "str_category_thumb")
    val strCategoryThumb: String? = null
)