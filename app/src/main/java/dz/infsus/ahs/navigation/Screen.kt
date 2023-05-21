package dz.infsus.ahs.navigation

sealed class Screen(val route: String){
    object SignUp: Screen("sign-up-screen")
    object LogIn: Screen("log-in-screen")
    object Home: Screen("home-screen")
    object Details: Screen("details-screen")
}
