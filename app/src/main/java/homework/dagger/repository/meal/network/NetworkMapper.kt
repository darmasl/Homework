package homework.dagger.repository.meal.network

import homework.dagger.common.model.Meal
import homework.dagger.repository.model.network.MealsResponse

fun mapFromNetwork(input: MealsResponse.Meal): Meal {
    return Meal(
        idMeal = input.idMeal,
        strMeal = input.strMeal,
        strMealThumb = input.strMealThumb
    )
}