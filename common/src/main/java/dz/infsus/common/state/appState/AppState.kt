package dz.infsus.common.state.appState

import arrow.optics.optics
import dz.infsus.common.state.appAccessState.AppAccessState
import dz.infsus.common.state.homeState.HomeState

@optics
data class AppState(
    val appAccessState: AppAccessState,
    val homeState: HomeState,
) {
    companion object {
        val Initial = AppState(
            appAccessState = AppAccessState.Initial,
            homeState = HomeState.Initial
        )
    }
}