package dz.infsus.common.di

import dz.infsus.common.state.appState.AppStateStore
import org.koin.dsl.module

val commonModule = module {
    single<AppStateStore> { AppStateStore() }
}