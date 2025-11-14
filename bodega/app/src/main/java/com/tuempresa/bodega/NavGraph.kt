package com.tuempresa.bodega

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tuempresa.bodega.ui.screens.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Categories : Screen("categories")
    object Products : Screen("products")
    object Customers : Screen("customers")
    object Orders : Screen("orders")
    object CreateOrder : Screen("createOrder")
    object OrderDetail : Screen("orderDetail/{orderId}") {
        fun withId(id: Int) = "orderDetail/$id"
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        // Las demás pantallas se agregarán luego
        // composable(Screen.Categories.route) { ... }
        // composable(Screen.Products.route) { ... }
        // composable(Screen.Customers.route) { ... }
        // composable(Screen.Orders.route) { ... }
        // ...
    }
}
