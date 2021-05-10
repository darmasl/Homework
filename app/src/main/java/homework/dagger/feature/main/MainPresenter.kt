package homework.dagger.feature.main

import homework.dagger.common.context.ActivityProvider
import homework.dagger.common.contract.BasePresenter
import homework.dagger.common.router.IRouter

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {
    private var router: IRouter = MainRouter(ActivityProvider.get())
}