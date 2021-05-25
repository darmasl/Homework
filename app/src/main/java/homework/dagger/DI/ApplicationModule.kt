package homework.dagger.DI

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import homework.dagger.App
import homework.dagger.common.interactor.Interactor
import homework.dagger.common.interactor.SingleRxInteractor
import homework.dagger.common.model.Category
import homework.dagger.common.network.RetrofitClient
import homework.dagger.common.utils.PreferencesUtils
import homework.dagger.feature.category.CategoryContract
import homework.dagger.feature.category.CategoryPresenter
import homework.dagger.feature.category.GetCategoriesInteractor
import homework.dagger.feature.category.categorydetails.CategoryDetailsContract
import homework.dagger.feature.category.categorydetails.CategoryDetailsPresenter
import homework.dagger.repository.AppDatabase
import homework.dagger.repository.category.CategoriesRepository
import homework.dagger.repository.category.CategoriesRepositoryImpl
import homework.dagger.repository.category.db.CategoriesDao
import homework.dagger.repository.category.db.LocalCategoriesDataSource
import homework.dagger.repository.category.db.LocalCategoriesDataSourceImpl
import homework.dagger.repository.category.network.CategoryService
import homework.dagger.repository.category.network.NetworkCategoriesDataSource
import homework.dagger.repository.category.network.NetworkCategoriesDataSourceImpl
import homework.dagger.repository.meal.MealsRepository
import homework.dagger.repository.meal.MealsRepositoryImpl
import homework.dagger.repository.meal.db.LocalMealDataSourceImpl
import homework.dagger.repository.meal.db.LocalMealsDataSource
import homework.dagger.repository.meal.db.MealsDao
import homework.dagger.repository.meal.network.MealsService
import homework.dagger.repository.meal.network.NetworkMealsDataSource
import homework.dagger.repository.meal.network.NetworkMealsDataSourceImpl
import io.reactivex.disposables.Disposable

@Module
abstract class ApplicationModule {

    @Binds
    @ApplicationScope
    abstract fun provideNetworkMeal(networkMealsDataSourceImpl: NetworkMealsDataSourceImpl): NetworkMealsDataSource

    @Binds
    @ApplicationScope
    abstract fun provideLocalMeal(localMealDataSourceImpl: LocalMealDataSourceImpl): LocalMealsDataSource

    @Binds
    @ApplicationScope
    abstract fun provideNetworkCategory(networkCategoriesDataSourceImpl: NetworkCategoriesDataSourceImpl): NetworkCategoriesDataSource

    @Binds
    @ApplicationScope
    abstract fun provideLocalCategory(localCategoriesDataSourceImpl: LocalCategoriesDataSourceImpl): LocalCategoriesDataSource

    @Binds
    @ApplicationScope
    abstract fun provideCategoryDetailsPresenter(categoryDetailsPresenter: CategoryDetailsPresenter): CategoryDetailsContract.Presenter

    @Binds
    @ApplicationScope
    abstract fun provideCategoriesPresenter(categoriesPresenter: CategoryPresenter): CategoryContract.Presenter

    @Binds
    @ApplicationScope
    abstract fun provideCategoriesRepository(categoriesRepositoryImpl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    @ApplicationScope
    abstract fun provideMealsRepository(mealsRepositoryImpl: MealsRepositoryImpl): MealsRepository

//    @Binds
//    @ApplicationScope
//    abstract fun provideCategoriesInteractor(getCategoriesInteractor: GetCategoriesInteractor): Interactor<Unit, List<Category>, Disposable>

    @Module
    companion object{

        @Provides
        @ApplicationScope
        fun provideApplicationContext(): Context {
            return App.app
        }

        @Provides
        @ApplicationScope
        fun providePreferenceUtils(): PreferencesUtils {
            return PreferencesUtils(App.app)
        }

        @Provides
        @ApplicationScope
        fun provideCategoryService(): CategoryService {
            return RetrofitClient.retrofit.create(CategoryService::class.java)
        }

        @Provides
        @ApplicationScope
        fun provideCategoriesDao(): CategoriesDao {
            return AppDatabase.getInstance(App.appContext).categoriesDao()
        }

        @Provides
        @ApplicationScope
        fun provideMealsService(): MealsService {
            return RetrofitClient.retrofit.create(MealsService::class.java)
        }

        @Provides
        @ApplicationScope
        fun provideMealsDao(): MealsDao {
            return AppDatabase.getInstance(App.appContext).mealsDao()
        }
    }

}