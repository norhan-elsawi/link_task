package com.norhan.linkdevelopment.utils.localDataProvider

import android.content.Context
import com.google.gson.Gson
import com.norhan.linkdevelopment.di.scope.ApplicationScope
import com.norhan.linkdevelopment.localDataProvider.SharedPreferencesUtils
import com.norhan.linkdevelopment.utils.ConnectivityUtils
import com.norhan.linkdevelopment.utils.locale.LocalLanguage
import javax.inject.Inject

/**
 * Created by Ahmed Abdelmoneam on 12/1/2016.
 */
@ApplicationScope
class LocalDataSourceImpl @Inject constructor(
    private val mContext: Context,
    private val mSharedPreferencesUtils: SharedPreferencesUtils,
    private val mGson: Gson
) : LocalDataSource {
    override val sharedPreferences: SharedPreferencesUtils
        get() = mSharedPreferencesUtils
    override val gson: Gson
        get() = mGson


    override fun changeLanguage(localLanguage: LocalLanguage): Boolean {
        if (localLanguage.id != mSharedPreferencesUtils.currentLanguage.id) {
            mSharedPreferencesUtils.putCurrentLanguage(localLanguage)
            return true
        }
        return false
    }

    override fun isConnectedToNetwork(): Boolean {
        return ConnectivityUtils.isConnected(mContext)
    }
}
