package com.shusharin.dollarrateapp.di

import com.shusharin.dollarrateapp.core.data.DateManager
import com.shusharin.dollarrateapp.core.data.DollarNotificationManager
import dagger.Binds
import dagger.Module

@Module
interface ServiceModule {

    @Binds
    fun provideDateManager(impl: DateManager.Base): DateManager

    @Binds
    fun provideDollarNotificationManager(impl: DollarNotificationManager.Base): DollarNotificationManager
}