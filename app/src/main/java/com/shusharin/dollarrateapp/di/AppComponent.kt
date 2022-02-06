package com.shusharin.dollarrateapp.di

import android.content.Context
import com.shusharin.dollarrateapp.MainActivity
import com.shusharin.dollarrateapp.core.RateApp
import com.shusharin.dollarrateapp.core.data.DollarJobService
import com.shusharin.dollarrateapp.ui.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, DataModule::class, DomainModule::class, ServiceModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)
    fun inject(dollarJobService: DollarJobService)
    fun inject(mainActivity: MainActivity)
    fun inject(app: RateApp)

    @Component.Factory
    interface AppComponentFactory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}