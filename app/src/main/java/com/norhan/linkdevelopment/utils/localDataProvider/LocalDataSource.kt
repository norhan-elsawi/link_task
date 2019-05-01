package com.norhan.linkdevelopment.utils.localDataProvider

import com.google.gson.Gson
import com.norhan.linkdevelopment.localDataProvider.SharedPreferencesUtils
import com.norhan.linkdevelopment.utils.locale.LocalLanguage

/**
 * Created by Ahmed Abdelmoneam on 12/1/2016.
 */
interface LocalDataSource {
    /**
     * Gets shared preferences.
     *
     * @return the shared preferences
     */
    val sharedPreferences: SharedPreferencesUtils


    /**
     * Gets gson.
     *
     * @return the gson
     */
    val gson: Gson

    fun changeLanguage(localLanguage: LocalLanguage): Boolean

    fun isConnectedToNetwork(): Boolean

}
