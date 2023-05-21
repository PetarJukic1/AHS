package dz.infsus.data.adverts.api

import dz.infsus.data.register.api.LogInRequest
import dz.infsus.data.register.api.LogInResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface AdvertsApi {
    @GET("/adverts")
    suspend fun getAdverts(
        @Query("minPrice") minPrice: Float,
        @Query("maxPrice") maxPrice: Float,
        @Query("city") city: String,
    ): List<Advert>
}

@Serializable
data class Advert(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("pictures")
    val pictures: List<String>,
    @SerialName("address")
    val address: String,
    @SerialName("city")
    val city: String,
    @SerialName("price_per_night")
    val pricePerNight: Float,
    @SerialName("username")
    val username: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("email")
    val email: String,
    @SerialName("reservations")
    val reservations: List<Reservation>
)

@Serializable
data class Reservation(
    @SerialName("id")
    val id: Int,
    @SerialName("startdate")
    val startDate: String,
    @SerialName("enddate")
    val endDate: String,
    @SerialName("userid")
    val userId: Int,
    @SerialName("advertid")
    val advertId: Int,
)