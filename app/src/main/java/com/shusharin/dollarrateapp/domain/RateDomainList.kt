package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.data.ErrorType
import com.shusharin.dollarrateapp.data.RateData
import com.shusharin.dollarrateapp.data.RateDataToDomainMapper
import com.shusharin.dollarrateapp.ui.RateUiList
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class RateDomainList : Abstract.Object<RateUiList, RateDomainListToUiMapper> {

    class Success(
        private val rates: List<RateData>,
        private val rateMapper: RateDataToDomainMapper,
    ) :
        RateDomainList() {
        override fun map(mapper: RateDomainListToUiMapper): RateUiList = mapper.map(rates.map {
            it.map(rateMapper)
        })
    }

    class Fail(private val e: Exception) : RateDomainList() {
        override fun map(mapper: RateDomainListToUiMapper): RateUiList =
            mapper.map(when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            })
    }
}
