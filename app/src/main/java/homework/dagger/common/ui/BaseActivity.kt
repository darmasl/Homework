package homework.dagger.common.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import homework.dagger.common.contract.IPresenter
import homework.dagger.common.contract.IView

abstract class BaseActivity<V : IView>(@LayoutRes contentLayoutId: Int = 0) :
    AppCompatActivity(contentLayoutId) {
    abstract val presenter: IPresenter<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this as V)
    }

    override fun onDestroy() {
        presenter.onViewDestroyed()
        super.onDestroy()
    }
}