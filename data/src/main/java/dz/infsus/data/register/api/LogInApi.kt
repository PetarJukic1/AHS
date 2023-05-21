package dz.infsus.data.register.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInApi {
    @POST("/login")
    suspend fun logIn(
        @Body logInApiRequest: LogInRequest,
    ): LogInResponse
}

@Serializable
data class LogInRequest(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)

@Serializable
data class LogInResponse(
    @SerialName("message")
    val message: String,
    @SerialName("userId")
    val userId: Int
)