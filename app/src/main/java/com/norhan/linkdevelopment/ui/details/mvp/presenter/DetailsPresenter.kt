package com.norhan.linkdevelopment.ui.details.mvp.presenter

import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.ui.details.mvp.contract.DetailsContract
import com.norhan.linkdevelopment.utils.base.BasePresenter
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.Article
import javax.inject.Inject

@ActivityScope
class DetailsPresenter @Inject
constructor(rootView: DetailsContract.View, model: DetailsContract.Model) :
    BasePresenter<DetailsContract.Model, DetailsContract.View>(rootView, model), DetailsContract.Presenter {

    override fun onViewReady() {
        //pass
    }

    override fun onViewReady(article: Article) {
        rootView.bindDataToView(article)
    }

}