package com.norhan.linkdevelopment.utils

import java.text.SimpleDateFormat
import java.util.*


object Utils {

    fun getFormattedDate(inputDate: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val date = format.parse(inputDate)
        val newFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)

        return newFormat.format(date)
    }

}