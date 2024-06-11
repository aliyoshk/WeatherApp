package com.example.weatherforecast.model

sealed class Screen(val route: String) {
    object LandingScreen: Screen(RouteTo.Landing.name)
    object DashboardScreen: Screen(RouteTo.Dashboard.name)
    object DetailsScreen: Screen(RouteTo.Details.name)
}