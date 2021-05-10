package homework.dagger.repository.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "meal",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["str_category"],
            childColumns = ["str_category"],
            onDelete = CASCADE
        )
    )
)
class MealEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_meal")
    val idMeal: String,
    @ColumnInfo(name = "str_meal")
    val strMeal: String? = null,
    @ColumnInfo(name = "str_meal_thumb")
    val strMealThumb: String? = null,
    @ColumnInfo(name = "str_category")
    val strCategory: String? = null
)