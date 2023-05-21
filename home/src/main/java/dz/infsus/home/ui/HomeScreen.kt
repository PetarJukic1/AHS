package dz.infsus.home.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun HomeScreen() {
    BackHandler {

    }

    Box(modifier = Modifier.fillMaxSize()){
        Text(
            modifier = Modifier.align(Alignment.Center),
            text ="Home"
        )
    }
}