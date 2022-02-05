package com.shusharin.dollarrateapp.core.data.util

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.shusharin.dollarrateapp.core.RateApp
import com.shusharin.dollarrateapp.data.net.RateCloudDataSource
import com.shusharin.dollarrateapp.domain.RateInteractor
import kotlinx.coroutines.*

class DollarJobService(
) : JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var calendar: DateManager
    private lateinit var notificationManager: DollarNotificationManager
    private lateinit var interactor: RateInteractor
    private lateinit var cloudDataSource: RateCloudDataSource

    override fun onCreate() {
        super.onCreate()
        calendar = (applicationContext as RateApp).calendar
        notificationManager = (applicationContext as RateApp).notificationManager
        interactor = (applicationContext as RateApp).rateInteractor
        cloudDataSource = (applicationContext as RateApp).cloudDataSource
    }

    override fun onStartJob(params: JobParameters?): Boolean {

        coroutineScope.launch {
            val rate = interactor.fetchLastRate()
            if (rate.isOver("75.0")) notificationManager.showNotification()
            jobFinished(params, true)
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onDestroy() {
        Log.d("сервис", "onDestroy")
        super.onDestroy()
        coroutineScope.cancel()
    }

    companion object {
        const val JOB_ID = 666
    }
}
