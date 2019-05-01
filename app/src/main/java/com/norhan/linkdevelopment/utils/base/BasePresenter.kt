package com.norhan.linkdevelopment.utils.base

import android.util.Log
import androidx.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

open class BasePresenter<M : BaseIModel, V : BaseIView> constructor(rootView: V, model: M) : BaseIPresenter {
    private var compositeDisposable: CompositeDisposable? = null
    var model: M
        private set
    var rootView: V
        private set

    init {
        this.model = model
        this.rootView = rootView
        onCreate()
    }

    @CallSuper
    final override fun onCreate() {
        compositeDisposable = CompositeDisposable()
    }

    override fun onViewReady() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        unSubscribe()
        this.compositeDisposable = null
    }

    override fun <T> subscribe(
        observable: Observable<T>, action: Consumer<T>,
        showDialog: Boolean
    ): Disposable {
        return subscribe(observable, object : Observer<T> {

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

            override fun onSubscribe(@io.reactivex.annotations.NonNull d: Disposable) {

            }

            override fun onNext(t: T) {
                try {
                    action.accept(t)
                } catch (e: Exception) {
                    Log.e("exception", e.message)
                }

            }
        }, showDialog)
    }

    override fun <T> subscribe(
        observable: Observable<T>,
        nextAction: Consumer<T>,
        errorAction: Consumer<Throwable>,
        showDialog: Boolean
    ): Disposable {
        return subscribe(observable, object : Observer<T> {
            override fun onError(e: Throwable) {
                try {
                    errorAction.accept(e)
                } catch (e1: Exception) {
                    Log.e("onError: ", e.message)
                }

            }

            override fun onComplete() {

            }

            override fun onSubscribe(@io.reactivex.annotations.NonNull d: Disposable) {

            }

            override fun onNext(t: T) {
                try {
                    nextAction.accept(t)
                } catch (e: Exception) {
                    Log.e("onError: ", e.message)
                }

            }
        }, showDialog)
    }


    private fun <T> getLoadingTransformer(
        showDialog: Boolean
    ): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { _ ->
                    if (showDialog) {
                        rootView.showLoading()
                    }
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    rootView.hideLoading()
                }
        }
    }

    private fun <T> subscribe(
        observable: Observable<T>, observer: Observer<T>,
        showDialog: Boolean
    ): Disposable {
        val disposable = observable
            .compose(getLoadingTransformer(showDialog))
            .subscribeWith(getObserver(observer))
        compositeDisposable!!.add(disposable)
        return disposable
    }

    private fun <T> getObserver(observer: Observer<T>): DisposableObserver<T> {
        return object : DisposableObserver<T>() {

            override fun onComplete() {
                observer.onComplete()
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }

            override fun onNext(t: T) {
                observer.onNext(t)
            }
        }
    }

    override fun addSubscription(subscription: Disposable) {
        compositeDisposable!!.add(subscription)
    }

    override fun unSubscribe() {
        dispose(compositeDisposable)
    }

    private fun dispose(compositeDisposable: CompositeDisposable?) {
        try {
            if (compositeDisposable != null && !compositeDisposable.isDisposed)
                compositeDisposable.clear()
        } catch (e: Exception) {
        }

    }

}