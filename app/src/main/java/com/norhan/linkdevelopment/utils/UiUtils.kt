package com.norhan.linkdevelopment.utils

import android.text.SpannableString
import android.text.style.UnderlineSpan

object UiUtils {

    fun setUnderLineText(msg: String): SpannableString {
        val content = SpannableString(msg)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        return content
    }

}