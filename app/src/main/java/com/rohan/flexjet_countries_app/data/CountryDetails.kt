package com.rohan.flexjet_countries_app.data

data class CountryDetails(
    val name: Name,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val population: Long,
    val area: Double?,
)


data class Name(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeName>
)

data class NativeName(
    val official: String,
    val common: String
)