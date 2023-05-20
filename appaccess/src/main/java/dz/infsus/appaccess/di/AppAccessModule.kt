package dz.infsus.appaccess.di

import dz.infsus.common.state.appAccessState.AppAccessViewStore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appAccessModule = module {
    viewModel<AppAccessViewStore> {
        AppAccessViewStore()
    }
}