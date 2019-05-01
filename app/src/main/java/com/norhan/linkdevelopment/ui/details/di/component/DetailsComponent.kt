package com.norhan.linkdevelopment.ui.details.di.component

import com.norhan.linkdevelopment.di.component.ApplicationComponent
import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.ui.details.di.module.DetailsModule
import com.norhan.linkdevelopment.ui.details.mvp.ui.activity.DetailsActivity
import dagger.Component

@ActivityScope
@Component(modules = [DetailsModule::class], dependencies = [ApplicationComponent::class])
interface DetailsComponent {
    fun inject(activity: DetailsActivity)
}
