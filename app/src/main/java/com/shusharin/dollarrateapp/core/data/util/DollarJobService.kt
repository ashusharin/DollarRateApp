package com.shusharin.dollarrateapp.core.data.util

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.util.Log
import com.shusharin.dollarrateapp.ui.ResourceProvider
import kotlinx.coroutines.*

class DollarJobService(
) : JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        val resourceProvider = ResourceProvider.Base(this)
        val notificationManager = DollarNotificationManager.Base(this, resourceProvider)

       coroutineScope.launch {
           for (i in 0 until 3) {
               delay(1000)
               Log.d("сервис","Timer $i")

           }
           notificationManager.showNotification()
            jobFinished(params, true)
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onDestroy() {
        Log.d("сервис","onDestroy")
        super.onDestroy()
        coroutineScope.cancel()
    }

    companion object {
        const val JOB_ID = 666
    }
}
