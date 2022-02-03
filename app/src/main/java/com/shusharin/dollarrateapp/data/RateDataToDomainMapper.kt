package com.shusharin.dollarrateapp.data

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.domain.RateDomain

interface RateDataToDomainMapper : Abstract.Mapper {

    fun map(
        value: String,
        date: String,
    ): RateDomain
}