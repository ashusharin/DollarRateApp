package com.shusharin.dollarrateapp.data.net

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.data.RateData

interface RateCloudListMapper : Abstract.Mapper {

    fun map(cloudList: List<Abstract.Object<RateData, RateCloudToDataMapper>>): List<RateData>

    class Base(private val rateMapper: RateCloudToDataMapper) : RateCloudListMapper {
        override fun map(cloudList: List<Abstract.Object<RateData, RateCloudToDataMapper>>): List<RateData> =
            cloudList.map { personCloud ->
                personCloud.map(rateMapper)
            }
    }
}
