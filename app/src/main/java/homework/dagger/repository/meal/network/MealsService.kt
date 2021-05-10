package homework.dagger.repository.meal.network

import homework.dagger.repository.model.network.MealsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsService {
    @GET("filter.php")
    fun getMealsByCategory(@Query("c") category: String): Single<MealsResponse>
}