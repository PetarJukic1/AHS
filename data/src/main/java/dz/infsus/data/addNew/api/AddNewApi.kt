package dz.infsus.data.addNew.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.POST

interface AddNewApi {
    @POST("/new")
    suspend fun addNew(
        @Body addNewRequest: AddNewRequestObject,
    )
}

@Serializable
data class AddNewRequestObject(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("address")
    val address: String,
    @SerialName("city")
    val city: String,
    @SerialName("price_per_night")
    val price_per_night: Float,
    @SerialName("ownerId")
    val ownerId: Int,
)