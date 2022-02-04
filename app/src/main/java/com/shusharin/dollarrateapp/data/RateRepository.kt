package com.shusharin.dollarrateapp.data

import com.shusharin.dollarrateapp.core.data.util.DateManager
import com.shusharin.dollarrateapp.data.net.RateCloudDataSource
import com.shusharin.dollarrateapp.data.net.RateCloudListMapper

interface RateRepository {

    suspend fun fetchRates(): RateDataList

    class Base(
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
    }
}