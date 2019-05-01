package com.norhan.linkdevelopment.utils.base

import com.norhan.linkdevelopment.utils.localDataProvider.LocalDataSource
import com.norhan.linkdevelopment.utils.remoteDataProvider.RemoteDataSource
import javax.inject.Inject

open class CommonRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CommonIRepository {

    override fun getLocalDataSource(): LocalDataSource {
        return localDataSource
    }

    override fun getRemoteDataSource(): RemoteDataSource {
        return remoteDataSource
    }
}