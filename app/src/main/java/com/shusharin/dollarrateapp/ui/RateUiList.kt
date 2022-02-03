package com.shusharin.dollarrateapp.ui

import com.shusharin.dollarrateapp.R
import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.data.ErrorType
import com.shusharin.dollarrateapp.domain.RateDomain
import com.shusharin.dollarrateapp.domain.RateDomainToUiMapper

sealed class RateUiList : Abstract.Object<Unit, RateUiCommunication> {

    class Success(
        private val rates: List<RateDomain>,
        private val rateMapper: RateDomainToUiMapper,
    ) : RateUiList() {
        override fun map(mapper: RateUiCommunication) {
            val ratesUi = rates.map {
                it.map(rateMapper)
            }
            mapper.map(ratesUi)
        }

    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider,
    ) : RateUiList() {
        override fun map(mapper: RateUiCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(RateUi.Fail(message)))
        }
    }
}