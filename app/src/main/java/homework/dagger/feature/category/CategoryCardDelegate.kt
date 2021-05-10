package homework.dagger.feature.category

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import homework.dagger.R
import homework.dagger.databinding.ItemCategoryBinding
import homework.dagger.feature.category.model.CategoryVO
import homework.dagger.feature.common.utils.loadUrl

fun categoryCardDelegate(onClick: ((CategoryVO) -> Unit)) =
    adapterDelegate<CategoryVO, CategoryVO>(R.layout.item_category) {

        with(ItemCategoryBinding.bind(itemView)) {

            root.setOnClickListener { onClick(item) }

            bind {
                title.text = item.strCategory
                val thumb = item.strCategoryThumb
                if (thumb != null) {
                    image.loadUrl(thumb, R.drawable.dish)
                } else {
                    image.setImageResource(R.drawable.dish)
                }
            }
        }
    }