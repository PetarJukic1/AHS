package dz.infsus.common.state.appAccessState

import arrow.optics.optics

@optics
data class AppAccessState(
    val appAccessSuccess: Boolean,
    val isError: Boolean,
    val email: String,
    val username: String,
    val password: String,
    val phoneNumber: String,
){
    companion object{
        val Initial = AppAccessState(
            appAccessSuccess = false,
            isError = false,
            email = "",
            username = "",
            password = "",
            phoneNumber = "",
        )
    }
}