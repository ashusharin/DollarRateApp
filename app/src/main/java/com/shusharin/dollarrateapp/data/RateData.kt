package com.shusharin.dollarrateapp.data

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.domain.RateDomain

class RateData(
    private val value: String = "13",
    private val date: String = "01.01.1990",
) : Abstract.Object<RateDomain, RateDataToDomainMapper>,
    Comparator {

    override fun map(mapper: RateDataToDomainMapper): RateDomain = mapper.map(value, date)
    override fun isSameDay(day: String): Boolean = day == date
    override fun isOver(dollarRate: String): Boolean = value.toDouble() > dollarRate.toDouble()
}


// FIXME: 05.02.2022 сделать лучше
interface Comparator {
    fun isSameDay(day: String): Boolean
    fun isOver(dollarRate: String): Boolean
}