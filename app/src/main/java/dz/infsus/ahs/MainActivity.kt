package dz.infsus.ahs

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import dz.infsus.ahs.application.AHSApp
import dz.infsus.ahs.navigation.SetupNavGraph
import dz.infsus.ahs.ui.theme.AHSTheme
import dz.infsus.domain.storeId.usecase.GetIdUsecase
import org.koin.androidx.compose.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            AHSApp()
            var scaffoldBottomPadding: Dp by remember { mutableStateOf(0.dp) }
            val navController = rememberNavController()

            Scaffold(
                scaffoldState = rememberScaffoldState(),
                bottomBar = {}
            ) { innerPadding ->
                scaffoldBottomPadding = innerPadding.calculateBottomPadding()
                SetupNavGraph(navController = navController)
            }
        }
    }
}
