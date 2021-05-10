package homework.dagger.repository.model.network


import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("categories")
    val categories: List<Category?>? = null
) {
    data class Category(
        @SerializedName("idCategory")
        val idCategory: String? = null,
        @SerializedName("strCategory")
        val strCategory: String? = null,
        @SerializedName("strCategoryDescription")
        val strCategoryDescription: String? = null,
        @SerializedName("strCategoryThumb")
        val strCategoryThumb: String? = null
    )
}