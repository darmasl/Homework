package homework.dagger.feature.meal

import homework.dagger.common.model.Meal
import homework.dagger.feature.meal.model.MealVO

fun mapToVO(input: Meal): MealVO {
    return MealVO(
        idMeal = input.idMeal ?: "",
        strMeal = input.strMeal,
        strMealThumb = input.strMealThumb
    )
}