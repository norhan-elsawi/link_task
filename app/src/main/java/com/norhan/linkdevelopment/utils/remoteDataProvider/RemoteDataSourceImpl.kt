package com.norhan.linkdevelopment.utils.remoteDataProvider

import com.norhan.linkdevelopment.utils.remoteDataProvider.api.GetArticleApi
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
) : RemoteDataSource {
    override fun getArticlesApi(): GetArticleApi {
       return retrofit.create(GetArticleApi::class.java)
    }

}