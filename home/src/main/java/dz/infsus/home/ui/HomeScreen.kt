package dz.infsus.home.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RangeSlider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import dz.infsus.common.state.homeState.HomeViewStore
import dz.infsus.common.ui.components.Button
import dz.infsus.common.ui.components.HorizontalSpacer
import dz.infsus.common.ui.components.TextField
import dz.infsus.common.ui.components.VerticalSpacer
import dz.infsus.common.ui.theme.ColorPallet
import dz.infsus.common.ui.theme.body4
import dz.infsus.common.ui.theme.subtitle4
import dz.infsus.domain.adverts.model.AdvertData
import dz.infsus.common.R
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onAddNewClicked: () -> Unit,
    goToDetails: () -> Unit,
    viewStore: HomeViewStore = getViewModel()
) {
    val state = viewStore()
    BackHandler {}

    LaunchedEffect(Unit) {
        viewStore.loadAdverts(state.minPrice, state.maxPrice, state.city)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.TopCenter)) {
            TopAppBar(
                backgroundColor = ColorPallet.neutral300,
                contentColor = ColorPallet.neutral0,
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Home",
                        style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral0),
                        textAlign = TextAlign.Center
                    )
                }
            }
            var sliderValues by remember {
                mutableStateOf(0.0f..1000.0f) // pass the initial values
            }

            VerticalSpacer(value = 24.dp)

            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = "Filter price range",
                style = MaterialTheme.typography.body1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            VerticalSpacer(value = 12.dp)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = String.format("%.2f", sliderValues.start),
                    style = MaterialTheme.typography.body1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                RangeSlider(
                    modifier = Modifier
                        .width(230.dp)
                        .align(Alignment.Center),
                    colors = SliderDefaults.colors(
                        activeTrackColor = ColorPallet.primary700,
                        inactiveTrackColor = ColorPallet.primary100,
                        thumbColor = ColorPallet.primary700,
                        activeTickColor = ColorPallet.primary700,
                        inactiveTickColor = ColorPallet.primary700,
                    ),
                    value = sliderValues,
                    onValueChange = { sliderValues_ ->
                        sliderValues = sliderValues_
                    },
                    valueRange = 0.0f..1000f,
                    onValueChangeFinished = {
                        viewStore.updateSelectedMinPrice(sliderValues.start)
                        viewStore.updateSelectedMaxPrice(sliderValues.endInclusive)
                    }
                )

                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = String.format("%.2f", sliderValues.endInclusive),
                    style = MaterialTheme.typography.body1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
            }

            VerticalSpacer(value = 24.dp)

            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = "Filter city",
                style = MaterialTheme.typography.body1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            VerticalSpacer(value = 12.dp)

            TextField(
                modifier = Modifier.padding(horizontal = 24.dp),
                onTextChanged = { viewStore.updateCity(it) },
                label = "City",
                placeholder = "City"
            )

            VerticalSpacer(value = 24.dp)

            Button(
                modifier = Modifier.fillMaxWidth(),
                text = "Apply",
                onClick = { viewStore.loadAdverts(state.minPrice, state.maxPrice, state.city) }
            )

            VerticalSpacer(value = 12.dp)

            Divider()

            LazyColumn {
                item{
                    VerticalSpacer(value = 12.dp)

                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = "Adverts",
                        style = MaterialTheme.typography.body1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center
                    )

                    VerticalSpacer(value = 12.dp)
                }
                items(state.adverts.adverts) { advert ->
                    AdvertView(
                        advert = advert,
                        onOpen = {
                            viewStore.selectAdvert(advertId = advert.id)
                            goToDetails()
                        }
                    )

                    VerticalSpacer(12.dp)
                }
            }
        }

        FloatingActionButton(
            backgroundColor = ColorPallet.neutral300,
            modifier = Modifier
                .padding(end = 24.dp, bottom = 24.dp)
                .size(48.dp)
                .align(Alignment.BottomEnd),
            onClick = onAddNewClicked
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "",
                    tint = ColorPallet.neutral30
                )
            }
        }
    }
}


@Composable
fun AdvertView(
    advert: AdvertData,
    onOpen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(color = ColorPallet.neutral30, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = advert.pictures[0],
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        VerticalSpacer(12.dp)

        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = "Title: ${advert.title}",
            style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral300, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        VerticalSpacer(value = 8.dp)

        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = "Price: ${advert.pricePerNight}$ per night",
            style = MaterialTheme.typography.body1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        VerticalSpacer(value = 8.dp)

        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = "Description",
            style = MaterialTheme.typography.body1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        VerticalSpacer(value = 8.dp)

        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = advert.description,
            style = MaterialTheme.typography.body2.copy(color = ColorPallet.neutral900),
            textAlign = TextAlign.Center
        )

        VerticalSpacer(value = 12.dp)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            padding = PaddingValues(0.dp),
            text = "Open",
            onClick = onOpen
        )
    }
}
