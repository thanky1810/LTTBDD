package com.example.hw2.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hw2.ui.age.AgeScreen
import com.example.hw2.ui.email.EmailScreen
import com.example.hw2.ui.th2.Th2Screen

@Composable
fun AppRoot() {
    val nav = rememberNavController()

    // Scaffold là tuỳ chọn, nhưng nên có để tự xử lý innerPadding
    Scaffold { inner ->
        NavHost(
            navController = nav,
            startDestination = Routes.Home,
            modifier = Modifier.padding(inner)
        ) {
            composable(Routes.Home) {
                Th2Screen(
                    onGoAge   = { nav.navigateSingleTop(Routes.Age) },
                    onGoEmail = { nav.navigateSingleTop(Routes.Email) }
                )
            }
            composable(Routes.Age) {
                AgeScreen(
                    onGoTh2   = { nav.navigateSingleTop(Routes.Home) },
                    onGoEmail = { nav.navigateSingleTop(Routes.Email) }
                )
            }
            composable(Routes.Email) {
                EmailScreen(
                    onGoTh2 = { nav.navigateSingleTop(Routes.Home) },
                    onGoAge = { nav.navigateSingleTop(Routes.Age) }
                )
            }
        }
    }
}

/** Tránh tạo nhiều bản sao màn khi bấm nhanh, và khôi phục state nếu có */
private fun androidx.navigation.NavHostController.navigateSingleTop(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.startDestinationId) { saveState = true }
    }
}
