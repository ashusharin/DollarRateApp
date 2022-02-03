package com.shusharin.dollarrateapp.domain

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.ui.RateUi

interface RateDomainToUiMapper : Abstract.Mapper {
    fun map(value: String, date: String):RateUi
}