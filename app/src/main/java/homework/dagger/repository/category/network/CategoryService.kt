package homework.dagger.repository.category.network

import homework.dagger.repository.model.network.CategoriesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CategoryService {
    @GET("categories.php")
    fun getCategories(): Single<CategoriesResponse>
}