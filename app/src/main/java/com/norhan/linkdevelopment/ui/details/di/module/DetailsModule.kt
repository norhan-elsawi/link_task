package com.norhan.linkdevelopment.ui.details.di.module

import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.ui.details.mvp.contract.DetailsContract
import com.norhan.linkdevelopment.ui.details.mvp.model.DetailsModel
import com.norhan.linkdevelopment.ui.details.mvp.repository.IDetailsRepository
import com.norhan.linkdevelopment.ui.details.mvp.repository.IDetailsRepositoryImpl
import com.norhan.linkdevelopment.utils.localDataProvider.LocalDataSource
import com.norhan.linkdevelopment.utils.remoteDataProvider.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DetailsModule(private val view: DetailsContract.View) {

    @ActivityScope
    @Provides
    internal fun provideDetailsView(): DetailsContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideDetailsModel(model: DetailsModel): DetailsContract.Model {
        return model
    }

    @ActivityScope
    @Provides
    internal fun provideDetailsRepository(
        remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource
    ): IDetailsRepository {
        return IDetailsRepositoryImpl(remoteDataSource, localDataSource)
    }
}