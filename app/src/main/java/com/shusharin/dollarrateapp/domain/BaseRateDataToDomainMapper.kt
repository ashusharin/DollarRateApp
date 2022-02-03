package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.data.RateDataToDomainMapper

class BaseRateDataToDomainMapper : RateDataToDomainMapper {
    override fun map(value: String, date: String) = RateDomain(value, date)
}