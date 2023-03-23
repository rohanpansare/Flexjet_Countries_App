package com.rohan.flexjet_countries_app.network

import com.rohan.flexjet_countries_app.data.CountryDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com/v3.1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val restApi: RestAPI = retrofit.create(RestAPI::class.java)

    suspend fun getCountryDetails(country: String): List<CountryDetails> {
        return restApi.getCountryDetails(country)
    }
}