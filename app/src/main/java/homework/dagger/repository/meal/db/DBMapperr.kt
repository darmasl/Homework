package homework.dagger.repository.meal.db

import homework.dagger.common.model.Category
import homework.dagger.common.model.Meal
import homework.dagger.repository.model.db.MealEntity

fun mapToDB(input: Meal, category: Category): MealEntity {
    return MealEntity(
        idMeal = input.idMeal ?: "",
        strMeal = input.strMeal,
        strMealThumb = input.strMealThumb,
        strCategory = category.strCategory
    )
}

fun mapFromDB(input: MealEntity): Meal {
    return Meal(
        idMeal = input.idMeal,
        strMeal = input.strMeal,
        strMealThumb = input.strMealThumb
    )
}