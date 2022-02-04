package com.shusharin.dollarrateapp.data

import com.shusharin.dollarrateapp.data.net.RateCloudDataSource
import com.shusharin.dollarrateapp.data.net.RateCloudListMapper

interface RateRepository {

    suspend fun fetchRates(): RateDataList

    class Base(
        private val cloudDataSource: RateCloudDataSource,
        private val rateCloudMapper: RateCloudListMapper,
    ) : RateRepository {
        override suspend fun fetchRates(): RateDataList = try {
            val listRateCloud = cloudDataSource.fetchRate()
            val rateList = rateCloudMapper.map(listRateCloud)
            RateDataList.Success(rateList)
        } catch (e: Exception) {
            RateDataList.Fail(e)
        }
    }
}