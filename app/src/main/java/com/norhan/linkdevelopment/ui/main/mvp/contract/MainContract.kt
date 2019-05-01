package com.norhan.linkdevelopment.ui.main.mvp.contract

import com.norhan.linkdevelopment.utils.base.BaseIModel
import com.norhan.linkdevelopment.utils.base.BaseIPresenter
import com.norhan.linkdevelopment.utils.base.BaseIView
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.Article
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.ArticlesResponse
import io.reactivex.Observable

interface MainContract {

    interface View : BaseIView {
        fun setUserName(name: String)
        fun setArticlesList(list: List<Article>)
        fun setRetryVisibility(isVisible: Boolean)
        fun navigateToDetailPage(data: Article)
        fun showNavigationMsg(id: Int)
    }

    interface Model : BaseIModel {
        fun getUserName(): String
        fun getArticles(): Observable<ArticlesResponse>
    }

    interface Presenter : BaseIPresenter {
        fun onRetryClicked()
        fun onArticlesItemClicked(data: Article)
        fun onNavigationItemClicked(id: Int)
    }


}
