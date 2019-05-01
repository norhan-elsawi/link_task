package com.norhan.linkdevelopment.ui.main.di.module

import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.ui.main.mvp.contract.MainContract
import com.norhan.linkdevelopment.ui.main.mvp.model.MainModel
import com.norhan.linkdevelopment.ui.main.mvp.repository.IMainRepository
import com.norhan.linkdevelopment.ui.main.mvp.repository.IMainRepositoryImpl
import com.norhan.linkdevelopment.utils.localDataProvider.LocalDataSource
import com.norhan.linkdevelopment.utils.remoteDataProvider.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val view: MainContract.View) {

    @ActivityScope
    @Provides
    internal fun provideMainView(): MainContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideMainModel(model: MainModel): MainContract.Model {
        return model
    }

    @ActivityScope
    @Provides
    internal fun provideMainRepository(
        remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource
    ): IMainRepository {
        return IMainRepositoryImpl(remoteDataSource, localDataSource)
    }
}