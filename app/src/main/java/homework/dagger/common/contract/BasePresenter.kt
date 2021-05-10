package homework.dagger.common.contract

import androidx.annotation.CallSuper

open class BasePresenter<V : IView> : IPresenter<V> {
    protected var view: V? = null
        private set

    @CallSuper
    override fun onViewCreated(view: V) {
        this.view = view
    }

    @CallSuper
    override fun onViewDestroyed() {
        this.view = null
    }
}