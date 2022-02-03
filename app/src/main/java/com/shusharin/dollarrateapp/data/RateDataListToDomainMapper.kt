package com.shusharin.dollarrateapp.data

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.domain.RateDomainList
import java.lang.Exception

interface RateDataListToDomainMapper : Abstract.Mapper {
    fun map(rates: List<RateData>): RateDomainList
    fun map(e: Exception): RateDomainList
}