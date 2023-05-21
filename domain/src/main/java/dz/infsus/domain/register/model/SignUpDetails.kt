package dz.infsus.domain.register.model

data class SignUpDetails(
    val username: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
)