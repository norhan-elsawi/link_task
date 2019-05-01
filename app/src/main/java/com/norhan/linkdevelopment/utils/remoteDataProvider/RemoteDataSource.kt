package com.norhan.linkdevelopment.utils.remoteDataProvider

import com.norhan.linkdevelopment.utils.remoteDataProvider.api.GetArticleApi

interface RemoteDataSource {
    fun getArticlesApi(): GetArticleApi
}