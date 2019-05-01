package  com.norhan.linkdevelopment.di.component

import android.content.Context
import com.norhan.linkdevelopment.di.module.ApplicationModule
import com.norhan.linkdevelopment.di.module.NetworkModule
import com.norhan.linkdevelopment.di.scope.ApplicationScope
import com.norhan.linkdevelopment.utils.localDataProvider.LocalDataSource
import com.norhan.linkdevelopment.utils.remoteDataProvider.RemoteDataSource

import com.squareup.picasso.Picasso
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun provideContext(): Context

    fun providePicasso(): Picasso

    fun provideLocalDataSource(): LocalDataSource

    fun provideRemoteDataSource(): RemoteDataSource
}