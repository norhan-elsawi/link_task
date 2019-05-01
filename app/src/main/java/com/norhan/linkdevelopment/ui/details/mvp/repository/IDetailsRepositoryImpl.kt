package com.norhan.linkdevelopment.ui.details.mvp.repository

import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.utils.base.CommonRepository
import com.norhan.linkdevelopment.utils.localDataProvider.LocalDataSource
import com.norhan.linkdevelopment.utils.remoteDataProvider.RemoteDataSource
import javax.inject.Inject

@ActivityScope
class IDetailsRepositoryImpl @Inject
constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) :
    CommonRepository(remoteDataSource, localDataSource), IDetailsRepository
