package homework.dagger.feature.meal

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import homework.dagger.R
import homework.dagger.databinding.ItemMealOverviewBinding
import homework.dagger.feature.common.utils.loadUrl
import homework.dagger.feature.meal.model.MealVO


fun mealOverviewCardDelegate(onClick: ((MealVO) -> Unit)) =
    adapterDelegateLayoutContainer<MealVO, MealVO>(R.layout.item_meal_overview) {

        with(ItemMealOverviewBinding.bind(itemView)) {
            root.setOnClickListener { onClick(item) }

            bind {
                title.text = item.strMeal
                val thumb = item.strMealThumb
                if (thumb != null) {
                    image.loadUrl(thumb, R.drawable.dish)
                } else {
                    image.setImageResource(R.drawable.dish)
                }
            }
        }
    }