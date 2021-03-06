package com.norhan.linkdevelopment.utils.locale

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import com.norhan.linkdevelopment.di.Injector
import java.util.*

/**
 * This class is used to change your application locale and persist this change for the next time
 * that your app is going to be used.
 *
 *
 * You can also change the locale of your application on the fly by using the setLocale method.
 *
 *
 * Created by gunhansancar on 07/10/15.
 */
object LocaleHelper {


    /**
     * On attach context.
     *
     * @param context the context
     * @return the context
     */
    fun onAttach(context: Context): Context {
        val lang = getPersistedData()
        return setLocale(context, lang)
    }

    /**
     * Sets locale.
     *
     * @param context  the context
     * @param language the language
     * @return the locale
     */
    private fun setLocale(context: Context, language: String): Context {
        /*persist(context, language);*/

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)

    }

    private fun getPersistedData(): String {
        return Injector.INSTANCE.appComponent!!.provideLocalDataSource()
            .sharedPreferences.currentLanguage.id
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }

        resources.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }
}