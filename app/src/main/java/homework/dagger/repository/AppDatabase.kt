package homework.dagger.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import homework.dagger.BuildConfig
import homework.dagger.repository.category.db.CategoriesDao
import homework.dagger.repository.meal.db.MealsDao
import homework.dagger.repository.model.db.CategoryEntity
import homework.dagger.repository.model.db.MealEntity

@Database(entities = [CategoryEntity::class, MealEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun mealsDao(): MealsDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "${BuildConfig.APPLICATION_ID}-db"
            )
                .fallbackToDestructiveMigration()
                .build().also {
                    instance = it
                }
        }

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: createInstance(context)
            }
        }
    }
}