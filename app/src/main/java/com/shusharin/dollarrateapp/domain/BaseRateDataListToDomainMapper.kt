package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.data.RateData
import com.shusharin.dollarrateapp.data.RateDataListToDomainMapper
import com.shusharin.dollarrateapp.data.RateDataToDomainMapper
import javax.inject.Inject

class BaseRateDataListToDomainMapper @Inject constructor(private val rateMapper: RateDataToDomainMapper) :
    RateDataListToDomainMapper {
    override fun map(rates: List<RateData>) = RateDomainList.Success(rates, rateMapper)

    override fun map(e: Exception): RateDomainList = RateDomainList.Fail(e)
}