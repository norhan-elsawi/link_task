package com.norhan.linkdevelopment.utils.base

import com.tbruyelle.rxpermissions2.RxPermissions

interface BaseIView {
    fun showMessage(s: String)
    fun showMessageInGreen(s: String)
    fun hideSoftKeyboard()
    fun getRxPermissions(): RxPermissions

    fun showErrorMsg(msg: Int)
    fun showErrorMsg(msg: String)

    fun showLoading()

    fun hideLoading()
}