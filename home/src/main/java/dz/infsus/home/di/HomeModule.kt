package dz.infsus.home.di

import dz.infsus.common.state.homeState.HomeViewStore
import dz.infsus.domain.adverts.usecase.GetAdvertsUsecase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel<HomeViewStore> {
        HomeViewStore(
            getAdverts = get<GetAdvertsUsecase>()
        )
    }
}