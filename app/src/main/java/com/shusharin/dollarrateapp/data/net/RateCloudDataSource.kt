package com.shusharin.dollarrateapp.data.net

interface RateCloudDataSource {
    suspend fun fetchRate(today: String, monthAgo: String): List<RateCloud>

    class Base(private val service: RateService) : RateCloudDataSource {
        override suspend fun fetchRate(today: String, monthAgo: String): List<RateCloud> {
            val rates = mutableListOf<RateCloud>()
            val response = service.fetchDollarRate(today, monthAgo)
            response.records?.forEach {
                val dollarRate = RateCloud(it.value, it.date, it.id)
                rates.add(dollarRate)
            }
            return rates
        }
    }
}