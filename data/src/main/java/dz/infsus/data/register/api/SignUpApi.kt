package dz.infsus.data.register.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {
    @POST("/register")
    suspend fun signUp(
        @Body signUpApiRequest: SignUpRequest,
    ): SingUpResponse
}

@Serializable
data class SignUpRequest(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("email")
    val email: String,
    @SerialName("phone_number")
    val phoneNumber: String,
)

@Serializable
data class SingUpResponse(
    @SerialName("message")
    val message: String,
    @SerialName("userId")
    val userId: Int,
)
