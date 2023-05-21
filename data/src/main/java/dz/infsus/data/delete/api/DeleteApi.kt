package dz.infsus.data.delete.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface DeleteApi {
    @POST("/del")
    suspend fun deleteAdvert(
        @Body deleteRequest: DeleteRequestBody,
    )
}

@Serializable
data class DeleteRequestBody(
    @SerialName("id")
    val id: Int,
    @SerialName("ownerId")
    val ownerId: Int,
 )