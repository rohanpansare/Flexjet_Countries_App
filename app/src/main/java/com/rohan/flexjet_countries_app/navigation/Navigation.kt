package com.rohan.flexjet_countries_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rohan.flexjet_countries_app.composables.CountryDetailsScreen
import com.rohan.flexjet_countries_app.composables.CountryListScreen
import com.rohan.flexjet_countries_app.data.getCountries

@Composable
fun NavHostContainer() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "countryList") {
        composable("countryList") {
            CountryListScreen(getCountries()) {
                // handle click
                navController.navigate("countryDetails/${it.name}")
            }
        }
        composable("countryDetails/{countryName}") { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName")
            val country = getCountries().find { it.name == countryName }!!
            CountryDetailsScreen(country)
        }
    }
}