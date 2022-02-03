package com.shusharin.dollarrateapp.ui

import com.shusharin.dollarrateapp.data.ErrorType
import com.shusharin.dollarrateapp.domain.RateDomain
import com.shusharin.dollarrateapp.domain.RateDomainListToUiMapper
import com.shusharin.dollarrateapp.domain.RateDomainToUiMapper

class BaseRateDomainListToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val ratesMapper: RateDomainToUiMapper,
) : RateDomainListToUiMapper {
    override fun map(rates: List<RateDomain>): RateUiList = RateUiList.Success(rates, ratesMapper)

    override fun map(errorType: ErrorType): RateUiList =
        RateUiList.Fail(errorType, resourceProvider)

}