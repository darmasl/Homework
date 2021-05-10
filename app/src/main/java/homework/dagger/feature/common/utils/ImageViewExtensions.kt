package homework.dagger.feature.common.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String, @DrawableRes placeholderRes: Int? = null) {
    Glide.with(this)
        .load(url)
        .apply {
            if (placeholderRes != null) {
                placeholder(placeholderRes)
            }
        }
        .into(this)
}