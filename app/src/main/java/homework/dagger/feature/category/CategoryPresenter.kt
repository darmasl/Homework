package homework.dagger.feature.category

import android.content.Context
import homework.dagger.R
import homework.dagger.common.context.ActivityProvider
import homework.dagger.common.contract.BasePresenter
import homework.dagger.common.interactor.Interactor
import homework.dagger.common.model.Category
import homework.dagger.common.router.IRouter
import homework.dagger.common.utils.PreferencesUtils
import homework.dagger.feature.category.model.CategoryVO
import homework.dagger.feature.main.MainRouter
import homework.dagger.repository.category.CategoriesRepository
import homework.dagger.repository.category.db.LocalCategoriesDataSource
import homework.dagger.repository.category.network.NetworkCategoriesDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CategoryPresenter @Inject constructor(
    val appContext: Context,
    private val preferencesUtils: PreferencesUtils,
    private val networkCategory: NetworkCategoriesDataSource,
    private val localCategory: LocalCategoriesDataSource,
    private val categoriesRepository: CategoriesRepository,
  //  private val getCategoriesInteractor: Interactor<Unit, List<Category>, Disposable>
) : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {
    private val router: IRouter = MainRouter(ActivityProvider.get())
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val getCategoriesInteractor: Interactor<Unit, List<Category>, Disposable> =
        GetCategoriesInteractor(categoriesRepository)
    private val refreshCategoriesInteractor: Interactor<Unit, List<Category>, Disposable> =
        RefreshCategoriesInteractor(categoriesRepository)

    override fun onRefresh() {
        view?.showProgress()
        compositeDisposable.add(
            refreshCategoriesInteractor.execute(
                params = Unit,
                onResult = ::handleCategories,
                onError = ::handleError
            )
        )
    }

    override fun openCategory(category: CategoryVO) {
        router.navigateTo(
            destination = R.id.categoryDetails,
            arg = category
        )
    }

    override fun onViewCreated(view: CategoryContract.View) {
        super.onViewCreated(view)
        loadCategories()
    }

    private fun loadCategories() {
        view?.showProgress()
        compositeDisposable.add(
            getCategoriesInteractor.execute(
                params = Unit,
                onResult = ::handleCategories,
                onError = ::handleError
            )
        )
    }

    private fun handleCategories(categories: List<Category>) {
        val toShow = categories.map(::mapToVO)
        view?.hideProgress()
        view?.showCategories(toShow)
    }

    private fun handleError(e: Throwable) {
        view?.hideProgress()
        view?.showError(
            title = "Network error",
            message = e.message
        )
    }
}