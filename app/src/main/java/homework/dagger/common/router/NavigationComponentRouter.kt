package homework.dagger.common.router

import androidx.collection.SparseArrayCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import homework.dagger.common.context.IActivityProvider

typealias Route = (NavController, Any?) -> Unit

open class NavigationComponentRouter(
    private val activityProvider: IActivityProvider
) : IRouter {
    private val routes = SparseArrayCompat<Route>()

    protected fun addRoute(destination: Int, route: Route) {
        routes.put(destination, route)
    }

    private fun getNavController(): NavController? {
        val activity = activityProvider.activity ?: return null
        val viewId = (activity as? RouterActivity)?.navControllerId ?: return null
        return activity.findNavController(viewId)
    }

    final override fun navigateTo(destination: Int, arg: Any?) {
        getNavController()?.let {
            routes[destination]?.invoke(it, arg)
        }
    }
}