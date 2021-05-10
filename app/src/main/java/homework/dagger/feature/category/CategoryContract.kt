package homework.dagger.feature.category

import homework.dagger.common.contract.IPresenter
import homework.dagger.common.contract.IView
import homework.dagger.feature.category.model.CategoryVO

interface CategoryContract {
    interface View : IView {
        fun showProgress()
        fun hideProgress()
        fun showError(title: String?, message: String?)
        fun showCategories(categories: List<CategoryVO>)
    }

    interface Presenter : IPresenter<View> {
        fun onRefresh()
        fun openCategory(category: CategoryVO)
    }
}