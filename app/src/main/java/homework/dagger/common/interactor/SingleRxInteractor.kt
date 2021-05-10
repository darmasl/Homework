package homework.dagger.common.interactor

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleRxInteractor<Params, Result>() : Interactor<Params, Result, Disposable> {
    abstract fun getSingle(
        params: Params
    ): Single<Result>

    override fun execute(
        params: Params,
        onResult: (Result) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return getSingle(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onResult, onError)
    }
}