package com.shusharin.dollarrateapp.data.net

import retrofit2.http.GET

interface RateService {
    // FIXME: 04.02.2022 поменять ссылку
    @GET("XML_dynamic.asp?date_req1=20/01/2022&date_req2=01/02/2022&VAL_NM_RQ=R01235")
    suspend fun fetchDollarRate(): RateResponse
}