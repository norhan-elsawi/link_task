package com.norhan.linkdevelopment.localDataProvider

import com.norhan.linkdevelopment.di.scope.ApplicationScope
import com.norhan.linkdevelopment.utils.localDataProvider.PrefHelper
import com.norhan.linkdevelopment.utils.locale.LocalLanguage
import javax.inject.Inject

/**
 * Created by Ahmed Abdelmoneam on 12/4/2016.
 */
@ApplicationScope
class SharedPreferencesUtils @Inject constructor(private val prefHelper: PrefHelper) {

    /**
     * Gets current language.
     *
     * @return the current language
     */
    val currentLanguage: LocalLanguage
        get() {
            val currentLanguageStr = prefHelper.getString(CURRENT_LANGUAGE, LocalLanguage.ENGLISH.id)
            return LocalLanguage.getLocalLanguageById(currentLanguageStr!!)
        }

    /**
     * Put current language.
     *
     * @param language the language
     */
    fun putCurrentLanguage(language: LocalLanguage) {
        prefHelper.putString(CURRENT_LANGUAGE, language.id)
    }

    companion object {

        /**
         * The constant CURRENT_LANGUAGE.
         */
        const val CURRENT_LANGUAGE = "current-language"

    }

}
