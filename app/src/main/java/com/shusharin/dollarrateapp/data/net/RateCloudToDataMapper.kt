package com.shusharin.dollarrateapp.data.net

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.data.RateData
import javax.inject.Inject

interface RateCloudToDataMapper : Abstract.Mapper {
    fun map(value: String, data: String): RateData

    class Base @Inject constructor() : RateCloudToDataMapper {
        override fun map(value: String, data: String) = RateData(value, data)
    }
}