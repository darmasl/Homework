package homework.dagger.common.contract

interface IPresenter<V : IView> {
    fun onViewCreated(view: V)
    fun onViewDestroyed()
}