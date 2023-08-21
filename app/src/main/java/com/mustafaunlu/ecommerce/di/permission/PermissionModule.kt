package com.mustafaunlu.ecommerce.di.permission

import android.app.Activity
import com.mustafaunlu.ecommerce.common.InternetPermissionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object PermissionModule {

    @Provides
    @Singleton
    fun providePermissionManager(activity: Activity): InternetPermissionManager {
        return InternetPermissionManager(activity)
    }
}
