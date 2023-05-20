package dz.infsus.ahs.navigation

sealed class Screen(val route: String){
    object SignUp: Screen("sign-up-screen")
}
