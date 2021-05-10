package homework.dagger.repository.model.network


import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName("meals")
    val meals: List<Meal?>? = null
) {
    data class Meal(
        @SerializedName("idMeal")
        val idMeal: String? = null,
        @SerializedName("strMeal")
        val strMeal: String? = null,
        @SerializedName("strMealThumb")
        val strMealThumb: String? = null
    )
}