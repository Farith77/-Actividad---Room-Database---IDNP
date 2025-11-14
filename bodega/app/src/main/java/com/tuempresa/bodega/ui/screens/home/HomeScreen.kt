package com.tuempresa.bodega.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tuempresa.bodega.Screen

@Composable
fun HomeScreen(nav: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = { nav.navigate(Screen.Categories.route) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Categorías") }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { nav.navigate(Screen.Products.route) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Productos") }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { nav.navigate(Screen.Customers.route) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Clientes") }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { nav.navigate(Screen.Orders.route) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Órdenes") }
    }
}
