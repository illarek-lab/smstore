package com.illareklab.smstore.ui.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.illareklab.smstore.ui.screens.PerfilScreen
import com.illareklab.smstore.ui.screens.TiendaScreen

// Cada pantalla se identifica con una "ruta" única
sealed class Ruta(
    val ruta: String,
    val etiqueta: String,
    val icono: ImageVector
) {
    data object Tienda : Ruta("tienda", "Tienda", Icons.Filled.Store)
    data object Perfil : Ruta("perfil", "Mi Perfil", Icons.Filled.Person)
}

private val pestañas = listOf(Ruta.Tienda, Ruta.Perfil)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    // Título dinámico según la pantalla activa
    val tituloActual = when (currentDestination?.route) {
        Ruta.Tienda.ruta -> "SanMarcos Store"
        Ruta.Perfil.ruta -> "Mi Perfil"
        else -> "SanMarcos Store"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(tituloActual) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ) {
                pestañas.forEach { pestaña ->
                    val seleccionada = currentDestination
                        ?.hierarchy?.any { it.route == pestaña.ruta } == true

                    NavigationBarItem(
                        selected = seleccionada,
                        onClick = {
                            navController.navigate(pestaña.ruta) {
                                // Evita acumular instancias en el back stack
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(pestaña.icono, contentDescription = pestaña.etiqueta)
                        },
                        label = { Text(pestaña.etiqueta) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Ruta.Tienda.ruta,
            modifier = Modifier.padding(padding)
        ) {
            composable(Ruta.Tienda.ruta) { TiendaScreen() }
            composable(Ruta.Perfil.ruta) { PerfilScreen() }
        }
    }
}