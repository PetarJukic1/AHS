package dz.infsus.appaccess.di

import dz.infsus.common.state.appAccessState.AppAccessViewStore
import dz.infsus.domain.register.usecase.LogInUsecase
import dz.infsus.domain.register.usecase.SignUpUsecase
import dz.infsus.domain.storeId.usecase.GetIdUsecase
import dz.infsus.domain.storeId.usecase.SetIdUsecase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appAccessModule = module {
    viewModel<AppAccessViewStore> {
        AppAccessViewStore(
            logIn = get<LogInUsecase>(),
            signUp = get<SignUpUsecase>(),
            getId = get<GetIdUsecase>(),
            setId = get<SetIdUsecase>(),
        )
    }
}