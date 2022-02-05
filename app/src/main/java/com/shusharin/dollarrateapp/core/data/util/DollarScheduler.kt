package com.shusharin.dollarrateapp.core.data.util

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.util.Log
import com.shusharin.dollarrateapp.core.data.util.DollarJobService.Companion.JOB_ID

class DollarScheduler(private val context: Context) {

    fun schedule() {
        Log.d("сервис", "метод schedule вызван")
        val componentName = ComponentName(context, DollarJobService::class.java)

        val jobInfo = JobInfo.Builder(JOB_ID, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//            .setPeriodic(ONE_DAY_INTERVAL)
            .build()

        val jobScheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }

    fun cancel() {
        val jobScheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(JOB_ID)

    }

    companion object {
//        const val ONE_DAY_INTERVAL = 24 * 60 * 60 * 1000L
        const val ONE_DAY_INTERVAL =  15 * 60 * 1000L

    }
}