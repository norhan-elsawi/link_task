package com.norhan.linkdevelopment.ui.main.mvp.model

import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.ui.main.mvp.contract.MainContract
import com.norhan.linkdevelopment.ui.main.mvp.repository.IMainRepository
import com.norhan.linkdevelopment.utils.base.BaseModel
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.ArticlesResponse
import io.reactivex.Observable
import javax.inject.Inject

@ActivityScope
class MainModel @Inject
constructor(private val commonRepository: IMainRepository) : BaseModel(commonRepository), MainContract.Model {
    override fun getArticles(): Observable<ArticlesResponse> {
        return commonRepository.getRemoteDataSource().getArticlesApi().execute()
    }

    override fun getUserName(): String {
        return "Tony Roshdy"
    }
}