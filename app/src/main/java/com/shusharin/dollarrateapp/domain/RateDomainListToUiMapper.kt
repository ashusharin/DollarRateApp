package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.data.ErrorType
import com.shusharin.dollarrateapp.ui.RateUiList

interface RateDomainListToUiMapper : Abstract.Mapper {
    fun map(rates: List<RateDomain>): RateUiList
    fun map(errorType: ErrorType): RateUiList
}