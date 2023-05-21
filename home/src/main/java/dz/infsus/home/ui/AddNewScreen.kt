package dz.infsus.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dz.infsus.common.state.homeState.HomeViewStore
import dz.infsus.common.ui.components.Button
import dz.infsus.common.ui.components.TextField
import dz.infsus.common.ui.components.VerticalSpacer
import dz.infsus.common.ui.theme.ColorPallet
import org.koin.androidx.compose.getViewModel

@Composable
fun AddNewScreen(
    viewStore: HomeViewStore = getViewModel()
) {
    val state = viewStore()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            backgroundColor = ColorPallet.neutral300,
            contentColor = ColorPallet.neutral0,
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Add new",
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral0),
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                VerticalSpacer(value = 24.dp)

                TextField(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onTextChanged = { viewStore.updateNewAdvertTitle(it) },
                    label = "Title",
                    placeholder = "Title",
                )

                VerticalSpacer(value = 12.dp)

                TextField(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onTextChanged = { viewStore.updateNewAdvertDescription(it) },
                    label = "Description",
                    placeholder = "Description",
                )

                VerticalSpacer(value = 12.dp)

                TextField(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onTextChanged = { viewStore.updateNewAdvertAddress(it) },
                    label = "Address",
                    placeholder = "Address",
                )

                VerticalSpacer(value = 12.dp)

                TextField(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onTextChanged = { viewStore.updateNewAdvertCity(it) },
                    label = "City",
                    placeholder = "City",
                )

                VerticalSpacer(value = 12.dp)

                TextField(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onTextChanged = { viewStore.updateNewAdvertPricePerNight(it) },
                    label = "Price Per Night",
                    placeholder = "Price Per Night",
                    isNumber = true,
                )

                VerticalSpacer(value = 24.dp)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                AnimatedVisibility(visible = state.successfulAdd) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Add new reservation success.",
                            style = MaterialTheme.typography.body2.copy(color = ColorPallet.systemSuccess700),
                            textAlign = TextAlign.Center
                        )

                        VerticalSpacer(value = 12.dp)
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    text = "Add New Advert",
                    onClick = { viewStore.addNew(state.addNewState) }
                )
            }
        }
    }
}