package dz.infsus.appaccess.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dz.infsus.common.state.appAccessState.AppAccessViewStore
import org.koin.androidx.compose.getViewModel

@Composable
fun AppAccessScreen(
    viewStore: AppAccessViewStore = getViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "AppAccessScreen"
        )
    }
}