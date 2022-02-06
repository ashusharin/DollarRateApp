package com.shusharin.dollarrateapp.di

import androidx.lifecycle.ViewModel
import com.shusharin.dollarrateapp.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("MainViewModel")
    @Binds
    fun bindListTripViewModel(impl: MainViewModel): ViewModel
}