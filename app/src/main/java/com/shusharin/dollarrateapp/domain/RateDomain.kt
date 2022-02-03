package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.ui.RateUi

class RateDomain(
    private val value: String,
    private val date: String,
) : Abstract.Object<RateUi, RateDomainToUiMapper> {
    override fun map(mapper: RateDomainToUiMapper) = mapper.map(value, date)
}