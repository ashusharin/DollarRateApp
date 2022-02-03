package com.shusharin.dollarrateapp.data

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.domain.RateDomainList
import java.lang.Exception

sealed class RateDataList() : Abstract.Object<RateDomainList, RateDataListToDomainMapper> {

    class Success(private val rates: List<RateData>) : RateDataList() {
        override fun map(mapper: RateDataListToDomainMapper): RateDomainList = mapper.map(rates)
    }

    class Fail(private val e: Exception) : RateDataList() {
        override fun map(mapper: RateDataListToDomainMapper): RateDomainList = mapper.map(e)
    }
}