package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.core.data.DateManager
import com.shusharin.dollarrateapp.data.RateData
import com.shusharin.dollarrateapp.data.RateDataListToDomainMapper
import com.shusharin.dollarrateapp.data.RateRepository
import javax.inject.Inject

interface RateInteractor {
    suspend fun fetchRates(): RateDomainList
    suspend fun fetchLastRate(): RateData

    class Base @Inject constructor(
        private val songRepository: RateRepository,
        private val mapper: RateDataListToDomainMapper,
        private val calendar: DateManager,
    ) : RateInteractor {
        override suspend fun fetchLastRate(): RateData {
            var result = RateData()
            val rateListData = songRepository.fetchLastRate()
            val today = calendar.getToday()
            for (rate in rateListData) {
                if (rate.isSameDay(today)) result = rate
            }
            return result
        }

        override suspend fun fetchRates(): RateDomainList =
            songRepository.fetchRates().map(mapper)
    }

}