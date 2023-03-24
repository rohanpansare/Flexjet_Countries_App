package com.rohan.flexjet_countries_app.composables

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rohan.flexjet_countries_app.data.Country
import com.rohan.flexjet_countries_app.data.CountryDetails
import com.rohan.flexjet_countries_app.network.RetrofitInstance
import com.rohan.flexjet_countries_app.ui.theme.Flexjet_Countries_AppTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CountryDetailsScreen(country: Country) {

    var countryDetails by remember { mutableStateOf(listOf<CountryDetails>()) }
    val context = LocalContext.current
    val retrofitInstance = RetrofitInstance()
    LaunchedEffect(country.name) {
        try {
            countryDetails = retrofitInstance.getCountryDetails(country.name)
            Log.d("Country", countryDetails.toString())
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Error retrieving country details for ${country.name}",
                Toast.LENGTH_SHORT
            ).show()
            Log.e("Country", "Error retrieving country details for ${country.name}", e)
        }
    }
    var capital = ""
    var population = ""
    var area = ""
    var region = ""
    var subRegion = ""
    for (countryDetail in countryDetails) {
        if (countryDetail.name.common == country.name || countryDetail.name.official == country.name) {
            capital = countryDetail?.capital?.joinToString() ?: "Null"
            population = countryDetail?.population?.toString() ?: "Null"
            area = countryDetail.area?.toString() ?: "Null"
            region = countryDetail?.region?.toString() ?: "Null"
            subRegion = countryDetail?.subregion?.toString() ?: "Null"
            break
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Country Details") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Capital:",
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = capital,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Region:",
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = region,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "SubRegion:",
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = subRegion,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Population:",
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = population,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Area:",
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "$area sq km",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Flexjet_Countries_AppTheme {
        CountryDetailsScreen(Country("India"))
    }
}

