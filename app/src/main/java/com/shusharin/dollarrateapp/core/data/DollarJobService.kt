package com.shusharin.dollarrateapp.core.data

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.shusharin.dollarrateapp.core.RateApp
import com.shusharin.dollarrateapp.data.net.RateCloudDataSource
import com.shusharin.dollarrateapp.data.sharePreference.SharePref
import com.shusharin.dollarrateapp.domain.RateInteractor
import kotlinx.coroutines.*
import javax.inject.Inject

class DollarJobService(
) : JobService() {

    private val appComponent by lazy {
        (application as RateApp).appComponent
    }
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    @Inject
    lateinit var calendar: DateManager
    lateinit var notificationManager: DollarNotificationManager
    lateinit var interactor: RateInteractor
    lateinit var sharePref: SharePref

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }

    override fun onStartJob(params: JobParameters?): Boolean {

        coroutineScope.launch {
            val newRate = interactor.fetchLastRate()
            val oldRate = sharePref.readOldRate()
            if (newRate.isOver(oldRate)) notificationManager.showNotification()
            jobFinished(params, true)
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    companion object {
        const val JOB_ID = 666
    }
}
