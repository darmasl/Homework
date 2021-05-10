package homework.dagger.feature.main

import homework.dagger.common.contract.IPresenter
import homework.dagger.common.contract.IView

interface MainContract {
    interface View : IView {
    }

    interface Presenter : IPresenter<View> {
    }
}