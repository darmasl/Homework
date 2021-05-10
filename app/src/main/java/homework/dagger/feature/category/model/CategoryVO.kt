package homework.dagger.feature.category.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryVO(
    val idCategory: String? = null,
    val strCategory: String? = null,
    val strCategoryDescription: String? = null,
    val strCategoryThumb: String? = null
) : Parcelable {
}