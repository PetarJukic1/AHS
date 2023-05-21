package dz.infsus.home.ui

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dz.infsus.common.state.homeState.HomeViewStore
import dz.infsus.common.ui.components.Button
import dz.infsus.common.ui.components.HorizontalSpacer
import dz.infsus.common.ui.components.VerticalSpacer
import dz.infsus.common.ui.theme.ColorPallet
import org.koin.androidx.compose.getViewModel
import java.util.Calendar
import java.util.Date

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

                DatePicker(
                    title = "Start Date Reservation",
                    date = state.startDate,
                    onDateSelected = { viewStore.changeStartReservationDate(it) }
                )

                VerticalSpacer(value = 12.dp)

                DatePicker(
                    title = "End Date Reservation",
                    date = state.endDate,
                    onDateSelected = { viewStore.changeEndReservationDate(it) }
                )

                VerticalSpacer(value = 12.dp)

                AnimatedVisibility(visible = state.reservationError) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Reservation fail try again.",
                            style = MaterialTheme.typography.body2.copy(color = ColorPallet.systemError700),
                            textAlign = TextAlign.Center
                        )

                        VerticalSpacer(value = 12.dp)
                    }
                }

                AnimatedVisibility(visible = state.reservationSuccess) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Reservation success.",
                            style = MaterialTheme.typography.body2.copy(color = ColorPallet.systemSuccess700),
                            textAlign = TextAlign.Center
                        )

                        VerticalSpacer(value = 12.dp)
                    }
                }

                Button(
                    text = "Reserve",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewStore.reserve(advertId = advert.id, startDate = state.startDate, endDate = state.endDate) }
                )
                
                VerticalSpacer(value = 32.dp)
            }
        }
    }
}

@Composable
fun DatePicker(
    title: String,
    date: String,
    onDateSelected: (String) -> Unit
) {

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onDateSelected("$mYear-${mMonth + 1}-$mDay")
        }, mYear, mMonth, mDay
    )

    mDatePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
        onDateSelected("$year-${month + 1}-$dayOfMonth")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
        )

        HorizontalSpacer(value = 12.dp)


        Text(
            text = date,
            modifier = Modifier.clickable {
                mDatePickerDialog.show()
            },
            style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900),
        )
    }
}
