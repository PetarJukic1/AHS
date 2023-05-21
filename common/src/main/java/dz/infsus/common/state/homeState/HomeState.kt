package dz.infsus.common.state.homeState

import arrow.optics.optics

@optics
data class HomeState(
    val test: String,
){
    companion object{
        val Initial = HomeState(
            test = ""
        )
    }
}
