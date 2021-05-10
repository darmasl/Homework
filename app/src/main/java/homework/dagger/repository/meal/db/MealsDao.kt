package homework.dagger.repository.meal.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import homework.dagger.repository.model.db.MealEntity
import io.reactivex.Single

@Dao
interface MealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeals(meals: List<MealEntity>)

    @Query("SELECT * from meal WHERE str_category = :category")
    fun getMealsForCategory(category: String): Single<List<MealEntity>>
}