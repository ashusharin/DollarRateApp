package com.shusharin.dollarrateapp.core

import android.app.Application
import com.shusharin.dollarrateapp.core.data.DateManager
import com.shusharin.dollarrateapp.core.data.DollarNotificationManager
import com.shusharin.dollarrateapp.core.data.DollarScheduler
import com.shusharin.dollarrateapp.data.RateRepository
import com.shusharin.dollarrateapp.data.net.RateCloudDataSource
import com.shusharin.dollarrateapp.data.sharePreference.SharePref
import com.shusharin.dollarrateapp.di.DaggerAppComponent
import com.shusharin.dollarrateapp.domain.RateInteractor
import com.shusharin.dollarrateapp.ui.*
import javax.inject.Inject

class RateApp : Application() {
    private companion object {
        const val BASE_URL = "http://cbr.ru/scripts/"
    }

    lateinit var mainViewModel: MainViewModel

    private val resourceProvider = ResourceProvider.Base(this)
    lateinit var rateRepository: RateRepository
    lateinit var rateInteractor: RateInteractor
    lateinit var notificationManager: DollarNotificationManager
    lateinit var calendar: DateManager
    lateinit var cloudDataSource: RateCloudDataSource
    lateinit var sharePref: SharePref

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    @Inject
    lateinit var scheduler: DollarScheduler

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        scheduler.schedule()
    }
}