package com.rohan.flexjet_countries_app.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rohan.flexjet_countries_app.data.Country
import com.rohan.flexjet_countries_app.data.CountryDetails
import com.rohan.flexjet_countries_app.network.RetrofitInstance

@Composable
fun CountryDetailsScreen(country: Country) {

    var countryDetails by remember { mutableStateOf(listOf<CountryDetails>()) }
    val retrofitInstance = RetrofitInstance()
    LaunchedEffect(country.name) {
        countryDetails = retrofitInstance.getCountryDetails(country.name)
        Log.d("Country", countryDetails.toString())
    }
    var capital = ""
    var population = ""
    var area = ""
    var region = ""
    var subRegion = ""
    for (countryDetail in countryDetails) {
        if (countryDetail.name.common == country.name) {
            capital = countryDetail.capital.joinToString()
            population = countryDetail.population.toString()
            area = countryDetail.area.toString()
            region = countryDetail.region
            subRegion = countryDetail.subregion
            break
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = country.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Capital: $capital",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Population: $population",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Area: $area sq km",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Region: $region",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "SubRegion: $subRegion",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}