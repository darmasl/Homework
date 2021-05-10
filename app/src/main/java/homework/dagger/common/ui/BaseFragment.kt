package homework.dagger.common.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import homework.dagger.common.contract.IPresenter
import homework.dagger.common.contract.IView

abstract class BaseFragment<V : IView>(@LayoutRes contentLayoutId: Int = 0) :
    Fragment(contentLayoutId) {
    abstract val presenter: IPresenter<V>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated(this as V)
    }

    override fun onDestroyView() {
        presenter.onViewDestroyed()
        super.onDestroyView()
    }
}