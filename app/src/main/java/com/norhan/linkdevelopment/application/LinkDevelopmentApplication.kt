package com.norhan.linkdevelopment.application

import android.app.Application
import android.content.res.Configuration
import com.norhan.linkdevelopment.di.Injector
import com.norhan.linkdevelopment.utils.locale.LocaleHelper

class LinkDevelopmentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeApplicationComponent()
    }

    private fun initializeApplicationComponent() {
        if (Injector.INSTANCE.appComponent == null) {
            Injector.INSTANCE.initializeAppComponent(this)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleHelper.onAttach(this)
    }
}