package homework.dagger.feature.category.categorydetails

import homework.dagger.common.contract.IPresenter
import homework.dagger.common.contract.IView
import homework.dagger.feature.category.model.CategoryVO
import homework.dagger.feature.meal.model.MealVO

interface CategoryDetailsContract {
    interface View : IView {
        fun showProgress()
        fun hideProgress()
        fun showError(title: String?, message: String?)
        fun showMeals(meals: List<MealVO>)
        fun showCategoryDescription(description: String?)
    }

    interface Presenter : IPresenter<View> {
        fun setCategory(category: CategoryVO)
        fun onRefresh()
    }
}