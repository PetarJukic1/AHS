package dz.infsus.common.state.appAccessState

import arrow.optics.optics

@optics
data class AppAccessState(
    val email: String,
    val username: String,
    val password: String,
    val phoneNumber: String,
){
    companion object{
        val Initial = AppAccessState(
            email = "",
            username = "",
            password = "",
            phoneNumber = "",
        )
    }
}