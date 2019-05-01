package  com.norhan.linkdevelopment.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.norhan.linkdevelopment.di.scope.ApplicationScope
import com.norhan.linkdevelopment.localDataProvider.SharedPreferencesUtils
import com.norhan.linkdevelopment.utils.Constants
import com.norhan.linkdevelopment.utils.localDataProvider.LocalDataSource
import com.norhan.linkdevelopment.utils.localDataProvider.LocalDataSourceImpl
import com.norhan.linkdevelopment.utils.remoteDataProvider.RemoteDataSource
import com.norhan.linkdevelopment.utils.remoteDataProvider.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class ApplicationModule(var mApplication: Application) {

    @Provides
    @ApplicationScope
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @ApplicationScope
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationScope
    fun provideSharedPrefs(): SharedPreferences {
        return mApplication.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @ApplicationScope
    fun provideLocalDataSource(
        mContext: Context,
        gson: Gson,
        sharedPreferencesUtils: SharedPreferencesUtils
    ): LocalDataSource {
        return LocalDataSourceImpl(mContext, sharedPreferencesUtils, gson)
    }


    @Provides
    @ApplicationScope
    fun provideRemoteDataSource(retrofit: Retrofit): RemoteDataSource {
        return RemoteDataSourceImpl(retrofit)
    }

}