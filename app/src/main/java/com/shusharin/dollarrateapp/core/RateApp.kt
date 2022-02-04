package com.shusharin.dollarrateapp.core

import android.app.Application
import com.shusharin.dollarrateapp.core.data.util.DateManager
import com.shusharin.dollarrateapp.core.data.util.DollarNotificationManager
import com.shusharin.dollarrateapp.core.data.util.DollarScheduler
import com.shusharin.dollarrateapp.data.RateRepository
import com.shusharin.dollarrateapp.data.net.RateCloudDataSource
import com.shusharin.dollarrateapp.data.net.RateCloudListMapper
import com.shusharin.dollarrateapp.data.net.RateCloudToDataMapper
import com.shusharin.dollarrateapp.data.net.RateService
import com.shusharin.dollarrateapp.domain.BaseRateDataListToDomainMapper
import com.shusharin.dollarrateapp.domain.BaseRateDataToDomainMapper
import com.shusharin.dollarrateapp.domain.RateInteractor
import com.shusharin.dollarrateapp.ui.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

class RateApp : Application() {
    private companion object {
        const val BASE_URL = "http://cbr.ru/scripts/"
    }

    lateinit var mainViewModel: MainViewModel

    private val base = ResourceProvider.Base(this)

    override fun onCreate() {
        super.onCreate()
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        val service = retrofit.create(RateService::class.java)
        val cloudDataSource = RateCloudDataSource.Base(service)
        val rateCloudToDataMapper = RateCloudToDataMapper.Base()
        val rateCloudMapper = RateCloudListMapper.Base(rateCloudToDataMapper)
        val calendar = DateManager.Base()
        val personRepository = RateRepository.Base(
            cloudDataSource,
            rateCloudMapper,
            calendar)
        val personsInteractor = RateInteractor.Base(
            personRepository,
            BaseRateDataListToDomainMapper(BaseRateDataToDomainMapper())
        )
        val resourceProvider = ResourceProvider.Base(this)
        val notificationManager = DollarNotificationManager.Base(this, resourceProvider)
        val a = DollarScheduler(this)
        a.schedule()
        val communication = RateUiCommunication.Base()
        mainViewModel = MainViewModel(
            personsInteractor,
            communication,
            BaseRateDomainListToUiMapper(resourceProvider,
                BaseRateDomainToUiMapper()),

            )
    }
}