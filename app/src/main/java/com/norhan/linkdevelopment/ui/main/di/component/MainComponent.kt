package com.norhan.linkdevelopment.ui.main.di.component

import com.norhan.linkdevelopment.di.component.ApplicationComponent
import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.ui.main.di.module.MainModule
import com.norhan.linkdevelopment.ui.main.mvp.ui.activity.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = [MainModule::class], dependencies = [ApplicationComponent::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}
