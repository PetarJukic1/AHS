package dz.infsus.data.reservation.api

import dz.infsus.data.register.api.LogInResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.POST

interface ReservationApi {
    @POST("/reserve")
    suspend fun reserve(
        @Body reservationBody: ReservationRequest,
    )
}

@Serializable
data class ReservationRequest(
    @SerialName("userId")
    val userId: Int,
    @SerialName("advertId")
    val advertId: Int,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("endDate")
    val endDate: String,
)