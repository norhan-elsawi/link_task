package com.norhan.linkdevelopment.di.module

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.picasso.OkHttp3Downloader
import com.norhan.linkdevelopment.BuildConfig
import com.norhan.linkdevelopment.di.scope.ApplicationScope
import com.norhan.linkdevelopment.utils.Constants
import com.norhan.linkdevelopment.utils.remoteDataProvider.ApiUrls
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @ApplicationScope
    fun providePicasso(client: OkHttpClient, app: Application): Picasso {

        val builder = Picasso.Builder(app)
            .downloader(OkHttp3Downloader(client))
            .listener { _, uri, _ -> Log.e("NetworkModule", "Error while loading image $uri") }

        if (BuildConfig.DEBUG) {
            builder.indicatorsEnabled(BuildConfig.DEBUG)
                .loggingEnabled(BuildConfig.DEBUG)
        }

        return builder.build()
    }

    /**
     * Provide gson gson.
     *
     * @return the gson
     */
    @Provides
    @ApplicationScope
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat(DATE_FORMAT)
        return gsonBuilder.create()
    }

    @Provides
    @ApplicationScope
    internal fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    /**
     * Provide common api retrofit retrofit.
     *
     * @param okHttpClient   the ok http client
     * @param gson           the gson
     * @param adapterFactory the adapter factory
     * @return the retrofit
     */
    @Provides
    @ApplicationScope
    internal fun provideCommonApiRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(ApiUrls.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        const val DATE_FORMAT = "MM/dd/yyyy HH:mm:ss a"
    }

}
