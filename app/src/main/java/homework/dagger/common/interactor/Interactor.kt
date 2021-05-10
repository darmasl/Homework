package homework.dagger.common.interactor

interface Interactor<Params, Result, Execution> {
    fun execute(params: Params, onResult: (Result) -> Unit, onError: (Throwable) -> Unit): Execution
}