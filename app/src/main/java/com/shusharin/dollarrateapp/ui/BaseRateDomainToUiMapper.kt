package com.shusharin.dollarrateapp.ui

import com.shusharin.dollarrateapp.domain.RateDomainToUiMapper

class BaseRateDomainToUiMapper : RateDomainToUiMapper {
    override fun map(value: String, date: String): RateUi = RateUi.Base(value,date)
}