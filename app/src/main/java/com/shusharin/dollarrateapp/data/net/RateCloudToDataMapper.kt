package com.shusharin.dollarrateapp.data.net

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.data.RateData

interface RateCloudToDataMapper : Abstract.Mapper {
    fun map(value: String, data: String): RateData

    class Base() : RateCloudToDataMapper {
        override fun map(value: String, data: String) = RateData(value, data)
    }
}