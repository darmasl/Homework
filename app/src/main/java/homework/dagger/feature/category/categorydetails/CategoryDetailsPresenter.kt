package homework.dagger.feature.category.categorydetails

import android.content.Context
import homework.dagger.App
import homework.dagger.common.contract.BasePresenter
import homework.dagger.common.interactor.Interactor
import homework.dagger.common.model.Category
import homework.dagger.common.model.Meal
import homework.dagger.common.network.RetrofitClient
import homework.dagger.common.utils.PreferencesUtils
import homework.dagger.feature.category.mapFromVO
import homework.dagger.feature.category.model.CategoryVO
import homework.dagger.feature.meal.mapToVO
import homework.dagger.repository.AppDatabase
import homework.dagger.repository.meal.MealsRepository
import homework.dagger.repository.meal.MealsRepositoryImpl
import homework.dagger.repository.meal.db.LocalMealDataSourceImpl
import homework.dagger.repository.meal.db.LocalMealsDataSource
import homework.dagger.repository.meal.network.MealsService
import homework.dagger.repository.meal.network.NetworkMealsDataSource
import homework.dagger.repository.meal.network.NetworkMealsDataSourceImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CategoryDetailsPresenter @Inject constructor(
    private val preferencesUtils: PreferencesUtils,
    private val networkMeal: NetworkMealsDataSource,
    private val localMeal: LocalMealsDataSource,
    private val mealsRepository: MealsRepository,
) : BasePresenter<CategoryDetailsContract.View>(),
    CategoryDetailsContract.Presenter {
    private var category: Category? = null

    private val getMealsForCategoryInteractor: Interactor<MealsForCategoryParams, List<Meal>, Disposable> =
        GetMealsForCategoryInteractor(mealsRepository)
    private val refreshMealsForCategoryInteractor: Interactor<MealsForCategoryParams, List<Meal>, Disposable> =
        RefreshMealsForCategoryInteractor(mealsRepository)

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onViewDestroyed() {
        compositeDisposable.clear()
        super.onViewDestroyed()
    }

    override fun setCategory(category: CategoryVO) {
        this.category = mapFromVO(category)
        view?.showCategoryDescription(this.category?.strCategoryDescription)
        loadMeals()
    }

    override fun onRefresh() {
        val category = this.category ?: return
        view?.showProgress()
        compositeDisposable.add(
            refreshMealsForCategoryInteractor.execute(
                params = MealsForCategoryParams(category),
                onError = ::handleError,
                onResult = ::handleMeals
            )
        )
    }

    private fun loadMeals() {
        val category = this.category ?: return
        view?.showProgress()
        compositeDisposable.add(
            getMealsForCategoryInteractor.execute(
                params = MealsForCategoryParams(category),
                onError = ::handleError,
                onResult = ::handleMeals
            )
        )
    }

    private fun handleMeals(meals: List<Meal>) {
        view?.hideProgress()
        view?.showMeals(meals.map(::mapToVO))
    }

    private fun handleError(e: Throwable) {
        view?.hideProgress()
        view?.showError(
            title = "Network error",
            message = e.message
        )
    }
}