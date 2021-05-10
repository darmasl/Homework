package homework.dagger.feature.meal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MealVO(
    val idMeal: String?,
    val strMeal: String?,
    val strMealThumb: String?
) : Parcelable