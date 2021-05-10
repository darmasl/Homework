package homework.dagger.common.router

interface IRouter {
    fun navigateTo(destination: Int, arg: Any? = null)
}