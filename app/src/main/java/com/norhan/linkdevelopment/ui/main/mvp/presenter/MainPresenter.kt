package com.norhan.linkdevelopment.ui.main.mvp.presenter

import com.norhan.linkdevelopment.R
import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.ui.main.mvp.contract.MainContract
import com.norhan.linkdevelopment.utils.base.BasePresenter
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.Article
import io.reactivex.functions.Consumer
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject
constructor(rootView: MainContract.View, model: MainContract.Model) :
    BasePresenter<MainContract.Model, MainContract.View>(rootView, model), MainContract.Presenter {
    override fun onViewReady() {
        rootView.setUserName(model.getUserName())
        getArticlesList()
    }

    override fun onRetryClicked() {
        getArticlesList()
    }

    override fun onArticlesItemClicked(data: Article) {
        rootView.navigateToDetailPage(data)
    }

    override fun onNavigationItemClicked(id: Int) {
        rootView.showNavigationMsg(id)
    }

    private fun getArticlesList() {
        rootView.setRetryVisibility(false)
        if (model.isConnectedToNetwork())
            subscribe(model.getArticles(),
                Consumer {
                    rootView.setArticlesList(it.articles)

                }, Consumer {
                    rootView.setRetryVisibility(true)
                    rootView.showErrorMsg(R.string.msg_some_thing_went_wrong)
                },
                true
            )
        else {
            rootView.setRetryVisibility(true)
            rootView.showErrorMsg(R.string.msg_check_your_connection_to_network)
        }
    }
}