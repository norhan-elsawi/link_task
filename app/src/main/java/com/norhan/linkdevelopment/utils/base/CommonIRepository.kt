package com.norhan.linkdevelopment.utils.base

import com.norhan.linkdevelopment.utils.localDataProvider.LocalDataSource
import com.norhan.linkdevelopment.utils.remoteDataProvider.RemoteDataSource

interface CommonIRepository {

    fun getRemoteDataSource(): RemoteDataSource
    fun getLocalDataSource(): LocalDataSource
}