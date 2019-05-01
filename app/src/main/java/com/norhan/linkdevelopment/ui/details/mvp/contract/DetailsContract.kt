package com.norhan.linkdevelopment.ui.details.mvp.contract

import com.norhan.linkdevelopment.utils.base.BaseIModel
import com.norhan.linkdevelopment.utils.base.BaseIPresenter
import com.norhan.linkdevelopment.utils.base.BaseIView
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.Article

interface DetailsContract {

    interface View : BaseIView{
        fun bindDataToView(article: Article)
    }

    interface Model : BaseIModel

    interface Presenter : BaseIPresenter {
        fun onViewReady(article: Article)
    }

}
