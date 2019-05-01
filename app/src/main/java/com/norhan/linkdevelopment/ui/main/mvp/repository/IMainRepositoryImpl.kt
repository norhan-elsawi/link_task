package com.norhan.linkdevelopment.ui.main.mvp.repository

import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.utils.base.CommonRepository
import com.norhan.linkdevelopment.utils.localDataProvider.LocalDataSource
import com.norhan.linkdevelopment.utils.remoteDataProvider.RemoteDataSource
import javax.inject.Inject

@ActivityScope
class IMainRepositoryImpl @Inject
constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) :
    CommonRepository(remoteDataSource, localDataSource), IMainRepository
