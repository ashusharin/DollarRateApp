package com.shusharin.dollarrateapp.data.net

import com.shusharin.dollarrateapp.core.Abstract
import com.shusharin.dollarrateapp.data.RateData

class RateCloud(
    var value: String,
    var date: String,
    var id: String,
) : Abstract.Object<RateData, RateCloudToDataMapper> {
    override fun map(mapper: RateCloudToDataMapper): RateData = mapper.map(value, date)
}

