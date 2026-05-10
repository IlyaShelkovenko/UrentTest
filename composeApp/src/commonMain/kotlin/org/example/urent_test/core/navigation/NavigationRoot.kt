package org.example.urent_test.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.urent_test.cities_list.presentation.CitiesList
import org.example.urent_test.city_information.presentation.CityInformation
import org.example.urent_test.city_information.presentation.CityParams

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.CitiesList
    ) {
        composable<Route.CitiesList> {
            CitiesList(
                onCityClick = { city ->
                    navController.navigate(
                        Route.CityInformation(
                            name = city.name,
                            country = city.country,
                            population = city.population
                        )
                    )
                }
            )
        }
        composable<Route.CityInformation> {
            val city = it.toRoute<Route.CityInformation>().name
            val country = it.toRoute<Route.CityInformation>().country
            val population = it.toRoute<Route.CityInformation>().population

            CityInformation(
                cityParams = CityParams(
                    name = city,
                    country = country,
                    population = population
                ),
                onGoBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}