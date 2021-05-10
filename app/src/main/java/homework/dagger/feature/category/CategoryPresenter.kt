package homework.dagger.feature.category

import homework.dagger.App
import homework.dagger.R
import homework.dagger.common.context.ActivityProvider
import homework.dagger.common.contract.BasePresenter
import homework.dagger.common.interactor.Interactor
import homework.dagger.common.model.Category
import homework.dagger.common.network.RetrofitClient
import homework.dagger.common.router.IRouter
import homework.dagger.common.utils.PreferencesUtils
import homework.dagger.feature.category.model.CategoryVO
import homework.dagger.feature.main.MainRouter
import homework.dagger.repository.AppDatabase
import homework.dagger.repository.category.CategoriesRepository
import homework.dagger.repository.category.CategoriesRepositoryImpl
import homework.dagger.repository.category.db.LocalCategoriesDataSourceImpl
import homework.dagger.repository.category.network.CategoryService
import homework.dagger.repository.category.network.NetworkCategoriesDataSourceImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {
    private val router: IRouter = MainRouter(ActivityProvider.get())
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val categoriesRepository: CategoriesRepository = CategoriesRepositoryImpl(
        networkSource = NetworkCategoriesDataSourceImpl(
            RetrofitClient.retrofit.create(CategoryService::class.java)
        ),
        localSource = LocalCategoriesDataSourceImpl(
            AppDatabase.getInstance(App.appContext).categoriesDao()
        ),
        preferencesUtils = PreferencesUtils(App.appContext)
    )

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