package uk.ac.tees.mad.w9646370

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    startDes: String
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = startDes,

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {
            composable(Route.home.name) {
                Home()
            }
            composable(Route.forgotPassword.name) {
                ForgotPassword(navHostController = navController)
            }
            composable(Route.login.name) {
                GetLogin(navController = navController)
            }
            composable(Route.register.name) {
                GetRegister(navController)
            }
        })
}