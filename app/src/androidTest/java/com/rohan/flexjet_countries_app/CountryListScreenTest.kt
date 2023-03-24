package com.rohan.flexjet_countries_app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rohan.flexjet_countries_app.data.Country
import com.rohan.flexjet_countries_app.ui.theme.Flexjet_Countries_AppTheme
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.Espresso


class CountryListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun verify_if_all_texts_exists() {

        // Start the app
        composeTestRule.setContent {
            Flexjet_Countries_AppTheme {
                MainScreen()
            }
        }
        // Wait for the app to load
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Choose a Country").assertExists()
        composeTestRule.onNodeWithText("Countries").assertExists()
    }


    @Test
    fun countryListScreenTest() {
        val countries = listOf(
            Country("Albania"),
            Country("Algeria"),
            Country("Angola")
        )
        composeTestRule.setContent {
            Flexjet_Countries_AppTheme {
                MainScreen()
            }
        }
        // Wait for the app to load
        composeTestRule.waitForIdle()
        // Click on a country card
        for(country in countries) {
            composeTestRule.onNodeWithText(country.name).performClick()
            composeTestRule.onNodeWithText(country.name).assertExists()
            Espresso.pressBack()
        }
    }
}

