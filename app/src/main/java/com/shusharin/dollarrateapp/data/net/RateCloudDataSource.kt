package com.shusharin.dollarrateapp.data.net

interface RateCloudDataSource {
    suspend fun fetchRate(): List<RateCloud>

    class Base(private val service: RateService) : RateCloudDataSource {
        override suspend fun fetchRate(): List<RateCloud> {
            val rates = mutableListOf<RateCloud>()
            val response = service.fetchDollarRate()
            response.records?.forEach {
                val dollarRate = RateCloud(it.value, it.date, it.id)
                rates.add(dollarRate)
            }
            return rates
        }
    }
}