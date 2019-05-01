package com.norhan.linkdevelopment.utils.base

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

interface BaseIPresenter {
    fun onCreate()
    fun onViewReady()
    fun onResume()
    fun onPause()
    fun onStart()
    fun onStop()
    fun onDestroy()

    fun <T> subscribe(observable: Observable<T>, action: Consumer<T>, showDialog: Boolean): Disposable

    fun <T> subscribe(
        observable: Observable<T>, nextAction: Consumer<T>,
        errorAction: Consumer<Throwable>, showDialog: Boolean
    ): Disposable

    fun addSubscription(subscription: Disposable)

    fun unSubscribe()

}