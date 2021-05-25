package homework.dagger.DI

import android.content.Context
import dagger.Component
import homework.dagger.common.utils.PreferencesUtils
import homework.dagger.feature.category.CategoriesFragment
import homework.dagger.feature.category.categorydetails.CategoryDetailsFragment
import homework.dagger.repository.category.network.CategoryService

@Component(
    modules = [ApplicationModule::class]
)
@ApplicationScope
interface AppComponent {
    val applicationContext: Context

    val preferenceUtils: PreferencesUtils

    val categoryService: CategoryService

    fun inject(categoriesFragment: CategoriesFragment)

    fun inject(categoryDetailsFragment: CategoryDetailsFragment)

}