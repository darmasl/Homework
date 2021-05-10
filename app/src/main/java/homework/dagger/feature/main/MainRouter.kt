package homework.dagger.feature.main

import homework.dagger.R
import homework.dagger.common.context.IActivityProvider
import homework.dagger.common.router.NavigationComponentRouter
import homework.dagger.feature.category.CategoriesFragmentDirections
import homework.dagger.feature.category.model.CategoryVO

class MainRouter(activityProvider: IActivityProvider) :
    NavigationComponentRouter(activityProvider) {
    init {
        addRoute(R.id.categoryDetails) { navController, arg ->
            arg as CategoryVO
            navController.navigate(CategoriesFragmentDirections.actionOpenCategoryDetails(arg))
        }
    }
}