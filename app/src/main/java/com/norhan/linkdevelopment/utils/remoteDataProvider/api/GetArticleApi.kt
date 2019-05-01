package com.norhan.linkdevelopment.utils.remoteDataProvider.api

import com.norhan.linkdevelopment.utils.remoteDataProvider.ApiUrls
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.ArticlesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface GetArticleApi {

    @GET(ApiUrls.GET_ARTICLES)
    fun execute(): Observable<ArticlesResponse>
}