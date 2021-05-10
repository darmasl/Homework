package homework.dagger.feature.main

import homework.dagger.R
import homework.dagger.common.router.RouterActivity
import homework.dagger.common.ui.BaseActivity

class MainActivity : BaseActivity<MainContract.View>(R.layout.main_activity), MainContract.View,
    RouterActivity {
    override val navControllerId: Int
        get() = R.id.host_container
    override val presenter: MainContract.Presenter = MainPresenter()
}
