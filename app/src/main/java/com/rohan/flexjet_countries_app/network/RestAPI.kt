package com.rohan.flexjet_countries_app.network

import com.rohan.flexjet_countries_app.data.CountryDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestAPI {
    @GET("name/{country}")
    suspend fun getCountryDetails(@Path("country") name: String): List<CountryDetails>
}
