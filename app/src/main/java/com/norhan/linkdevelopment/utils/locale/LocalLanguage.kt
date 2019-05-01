package com.norhan.linkdevelopment.utils.locale

import android.text.TextUtils
import androidx.annotation.StringRes
import com.norhan.linkdevelopment.R
import java.util.*


enum class LocalLanguage constructor(id: String, @StringRes displayName: Int) {
    /**
     * Arabic local language.
     */
    ARABIC("ar", R.string.arabic),
    /**
     * English local language.
     */
    ENGLISH("en", R.string.english);
    /**
     * The Id.
     */
    /**
     * Gets id.
     *
     * @return the id
     */
    var id: String
        internal set
    /**
     * The Display name.
     */
    /**
     * Gets display name.
     *
     * @return the display name
     */
    @get:StringRes
    var displayName: Int = 0
        internal set

    init {
        this.id = id
        this.displayName = displayName
    }

    companion object {

        /**
         * Gets local language by id.
         *
         * @param id the id
         * @return the local language by id
         */
        fun getLocalLanguageById(id: String): LocalLanguage {
            return if (TextUtils.isEmpty(id)) {
                if (LocalLanguage.ARABIC.id
                        .equals(Locale.getDefault().language, ignoreCase = true)
                )
                    LocalLanguage.ARABIC
                else
                    LocalLanguage.ENGLISH
            } else if (ARABIC.id.equals(id, ignoreCase = true)) {
                ARABIC
            } else if (ENGLISH.id.equals(id, ignoreCase = true)) {
                ENGLISH
            } else {
                ENGLISH
            }
        }
    }
}
