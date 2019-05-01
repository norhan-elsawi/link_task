package com.norhan.linkdevelopment.di

import com.norhan.linkdevelopment.application.LinkDevelopmentApplication
import com.norhan.linkdevelopment.di.component.ApplicationComponent
import com.norhan.linkdevelopment.di.component.DaggerApplicationComponent
import com.norhan.linkdevelopment.di.module.ApplicationModule
import com.norhan.linkdevelopment.di.module.NetworkModule


/**
 * Created by Ahmed Abdelmoneam on 12/1/2016.
 */
enum class Injector {
    /**
     * Instance injector.
     */
    INSTANCE;

    /**
     * Gets app component.
     *
     * @return the app component
     */
    var appComponent: ApplicationComponent? = null
        private set

    /**
     * Initialize app component application component.
     *
     * @param application the application
     * @return the application component
     */
    fun initializeAppComponent(application: LinkDevelopmentApplication): ApplicationComponent {
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(application))
            .networkModule(NetworkModule())
            .build()
        return appComponent!!
    }
}
