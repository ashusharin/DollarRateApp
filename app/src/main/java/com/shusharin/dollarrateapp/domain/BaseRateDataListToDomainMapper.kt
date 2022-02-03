package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.data.RateData
import com.shusharin.dollarrateapp.data.RateDataListToDomainMapper
import com.shusharin.dollarrateapp.data.RateDataToDomainMapper

class BaseRateDataListToDomainMapper(private val rateMapper: RateDataToDomainMapper) :
    RateDataListToDomainMapper {
    override fun map(rates: List<RateData>) = RateDomainList.Success(rates, rateMapper)

    override fun map(e: Exception): RateDomainList = RateDomainList.Fail(e)
}