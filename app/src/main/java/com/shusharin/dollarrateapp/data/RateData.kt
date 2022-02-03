package com.shusharin.dollarrateapp.data

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.domain.RateDomain

class RateData(
    private val value: String,
    private val date: String,
) : Abstract.Object<RateDomain, RateDataToDomainMapper> {

    override fun map(mapper: RateDataToDomainMapper): RateDomain = mapper.map(value, date)
}