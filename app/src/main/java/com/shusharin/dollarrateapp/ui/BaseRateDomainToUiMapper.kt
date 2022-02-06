package com.shusharin.dollarrateapp.ui

import com.shusharin.dollarrateapp.domain.RateDomainToUiMapper
import javax.inject.Inject

class BaseRateDomainToUiMapper @Inject constructor() : RateDomainToUiMapper {
    override fun map(value: String, date: String): RateUi = RateUi.Base(value,date)
}