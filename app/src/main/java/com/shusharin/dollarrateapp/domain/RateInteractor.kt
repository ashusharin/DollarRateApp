package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.data.RateDataListToDomainMapper
import com.shusharin.dollarrateapp.data.RateRepository

interface RateInteractor {
    suspend fun findListSong(): RateDomainList

    class Base(
        private val songRepository: RateRepository,
        private val mapper: RateDataListToDomainMapper,
    ) : RateInteractor {
        override suspend fun findListSong(): RateDomainList =
            songRepository.fetchRates().map(mapper)
    }
}