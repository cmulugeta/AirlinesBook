package me.cmulugeta.airlinesbook.extensions.rx

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mohammed Fathy.
 * dev.cmulugeta@gmail.com
 */

fun <T : Any> Single<T>.subscribeAndObserve(): Single<T> =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Flowable<T>.subscribeAndObserve(): Flowable<T> =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Observable<T>.subscribeAndObserve(): Observable<T> =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Flowable<T>.observeMainThread(): Flowable<T> =
        observeOn(AndroidSchedulers.mainThread())

fun Completable.subscribeAndObserve(): Completable =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())