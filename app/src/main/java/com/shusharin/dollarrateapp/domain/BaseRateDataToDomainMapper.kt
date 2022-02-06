package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.data.RateDataToDomainMapper
import javax.inject.Inject

class BaseRateDataToDomainMapper @Inject constructor() : RateDataToDomainMapper {
    override fun map(value: String, date: String) = RateDomain(value, date)
}