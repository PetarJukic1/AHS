package dz.infsus.ahs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dz.infsus.appaccess.ui.AppAccessScreen
import dz.infsus.home.ui.HomeScreen
import dz.infsus.appaccess.ui.LoginScreen
import dz.infsus.domain.storeId.usecase.GetIdUsecase
import dz.infsus.home.ui.AddNewScreen
import dz.infsus.home.ui.DetailsScreen
import org.koin.androidx.compose.inject

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignUp.route
    ) {
        composable(Screen.SignUp.route) {
            AppAccessScreen(onRegisterSuccess = {
                navController.navigate(Screen.Home.route)
            },
                onLogInClicked = { navController.navigate(Screen.LogIn.route) })
        }
        composable(Screen.LogIn.route) {
            LoginScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onAddNewClicked = {navController.navigate(Screen.AddNew.route) },
                goToDetails = { navController.navigate(Screen.Details.route) }
            )
        }
        composable(Screen.Details.route) {
            DetailsScreen(onDelete = {navController.popBackStack()})
        }
        composable(Screen.AddNew.route) {
            AddNewScreen()
        }
    }
}