package com.shusharin.dollarrateapp.data.net

import retrofit2.http.GET
import retrofit2.http.Query

interface RateService {
    // FIXME: 04.02.2022 поменять ссылку
    @GET("XML_dynamic.asp?VAL_NM_RQ=R01235")
    suspend fun fetchDollarRate(
        @Query("date_req1") today: String,
        @Query("date_req2") monthAgo: String,
    ): RateResponse
}