package dz.infsus.home.di

import dz.infsus.common.state.homeState.HomeViewStore
import dz.infsus.domain.adverts.usecase.GetAdvertsUsecase
import dz.infsus.domain.reservation.usecase.ReserveUsecase
import dz.infsus.domain.storeId.usecase.GetIdUsecase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel<HomeViewStore> {
        HomeViewStore(
            getAdverts = get<GetAdvertsUsecase>(),
            getId = get<GetIdUsecase>(),
            makeReservation = get<ReserveUsecase>(),
        )
    }
}