package dz.infsus.common.state.homeState

import dz.infsus.common.state.appState.AppState
import dz.infsus.common.state.appState.homeState
import dz.infsus.common.viewstore.ViewStore

class HomeViewStore(
) : ViewStore<HomeState>(AppState.homeState) {

}