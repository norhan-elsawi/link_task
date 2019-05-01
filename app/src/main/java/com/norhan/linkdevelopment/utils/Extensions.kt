package com.norhan.linkdevelopment.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getColorCompat(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}


fun Context.getImageDrawable(@DrawableRes imageResId: Int): Drawable? {
    return ContextCompat.getDrawable(this, imageResId)
}

fun Context.getDrawableByCode(code: String): Drawable? {
    return getImageDrawable(resources.getIdentifier(code, "drawable", packageName))
}
