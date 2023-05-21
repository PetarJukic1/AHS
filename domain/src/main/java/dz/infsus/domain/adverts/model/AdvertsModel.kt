package dz.infsus.domain.adverts.model

import java.util.Date


data class AdvertsModel(
    val adverts: List<AdvertData>
)

data class AdvertData(
    val id: Int,
    val title: String,
    val description: String,
    val pictures: List<String>,
    val address: String,
    val city: String,
    val pricePerNight: Float,
    val username: String,
    val phoneNumber: String,
    val email: String,
    val reservations: List<ReservationModel>
)

data class ReservationModel(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val userId: Int,
    val advertId: Int,
)