package dz.infsus.common.state.appAccessState

import dz.infsus.common.state.appState.AppState
import dz.infsus.common.state.appState.appAccessState
import dz.infsus.common.viewstore.ViewStore

class AppAccessViewStore(): ViewStore<AppAccessState>(AppState.appAccessState){}