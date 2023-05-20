package dz.infsus.common.state.appState

import arrow.optics.optics
import dz.infsus.common.state.appAccessState.AppAccessState

@optics
data class AppState(
    val appAccessState: AppAccessState,
) {
    companion object {
        val Initial = AppState(
            appAccessState = AppAccessState.Initial,
        )
    }
}