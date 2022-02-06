package com.shusharin.dollarrateapp.data

import com.shusharin.dollarrateapp.core.data.DateManager
import com.shusharin.dollarrateapp.data.net.RateCloudDataSource
import com.shusharin.dollarrateapp.data.net.RateCloudListMapper
import javax.inject.Inject

interface RateRepository {

    suspend fun fetchRates(): RateDataList
    suspend fun fetchLastRate(): List<RateData>
    class Base @Inject constructor(
        private val cloudDataSource: RateCloudDataSource,
        private val rateCloudMapper: RateCloudListMapper,
        private val calendar: DateManager,
    ) : RateRepository {
        override suspend fun fetchRates(): RateDataList = try {
            val dateRange = calendar.getRange()
            val listRateCloud = cloudDataSource.fetchRate(dateRange.first, dateRange.second)
            val rateList = rateCloudMapper.map(listRateCloud)
            RateDataList.Success(rateList)
        } catch (e: Exception) {
            RateDataList.Fail(e)
        }

        override suspend fun fetchLastRate(): List<RateData> = try {
            val dateRange = calendar.getTenDay()
            val listRateCloud = cloudDataSource.fetchRate(dateRange.first, dateRange.second)
            val rateList = rateCloudMapper.map(listRateCloud)
            rateList
        } catch (e: java.lang.Exception){
            emptyList<RateData>() // FIXME: 05.02.2022 подумать как улучшить
        }
    }
}