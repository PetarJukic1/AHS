package dz.infsus.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dz.infsus.common.state.homeState.HomeViewStore
import dz.infsus.common.ui.components.HorizontalSpacer
import dz.infsus.common.ui.components.VerticalSpacer
import dz.infsus.common.ui.theme.ColorPallet
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailsScreen(
    viewStore: HomeViewStore = getViewModel()
) {
    val state = viewStore()
    val advert = state.adverts.adverts.firstOrNull { it.id == state.selectedAdvertId } ?: return
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            TopAppBar(
                backgroundColor = ColorPallet.neutral300,
                contentColor = ColorPallet.neutral0,
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Advert: ${advert.title}",
                        style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral0),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                VerticalSpacer(value = 24.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Images",
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(value = 24.dp)

                LazyRow {
                    item { HorizontalSpacer(value = 24.dp) }
                    items(advert.pictures) {
                        AsyncImage(
                            modifier = Modifier.size(300.dp),
                            model = it,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )

                        HorizontalSpacer(value = 24.dp)
                    }
                }

                VerticalSpacer(value = 24.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Address",
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(value = 12.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = advert.address,
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900),
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(value = 12.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "City",
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(value = 12.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = advert.city,
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900),
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(value = 12.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Price",
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(value = 12.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "${advert.pricePerNight}$ per night",
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900),
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(value = 12.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Description",
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(value = 12.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = advert.description,
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900),
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(value = 12.dp)
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Reservations",
                    style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                VerticalSpacer(value = 12.dp)

                advert.reservations.forEach {
                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = "Reserved from ${it.startDate} to ${it.endDate}",
                        style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900),
                        textAlign = TextAlign.Start
                    )

                    VerticalSpacer(value = 12.dp)
                }
            }
        }
    }
}