package me.cmulugeta.airlinesbook.domain.interactor

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber
import me.cmulugeta.airlinesbook.domain.executor.ExecutionThread

/**
 * FlowableUseCase is is an abstract class which provide a Flowable observable to
 * emit required data or error.
 *
 * This observable support backpressure.
 */
abstract class FlowableUseCase<T, in Params> constructor(
        private val subscriberThread: ExecutionThread,
        private val postExecutionThread: ExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Flowable<T>

    open fun execute(singleObserver: DisposableSubscriber<T>, params: Params? = null) {
        val single = this.buildUseCaseObservable(params)
                .subscribeOn(subscriberThread.scheduler)
                .observeOn(postExecutionThread.scheduler)
        addDisposable(single.subscribeWith(singleObserver))
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}