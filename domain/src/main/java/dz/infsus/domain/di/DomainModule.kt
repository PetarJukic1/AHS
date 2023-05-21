package dz.infsus.domain.di

import dz.infsus.domain.register.repository.RegisterRepository
import dz.infsus.domain.register.usecase.LogInUsecase
import dz.infsus.domain.register.usecase.SignUpUsecase
import dz.infsus.domain.storeId.repository.StoreIdRepository
import dz.infsus.domain.storeId.usecase.GetIdUsecase
import dz.infsus.domain.storeId.usecase.SetIdUsecase
import org.koin.dsl.module

val domainModule = module {
    single<SignUpUsecase>{
        SignUpUsecase.Default(
            repository = get<RegisterRepository>()
        )
    }

    single<LogInUsecase>{
        LogInUsecase.Default(
            repository = get<RegisterRepository>()
        )
    }

    single<GetIdUsecase>{
        GetIdUsecase.Default(
            repository = get<StoreIdRepository>()
        )
    }

    single<SetIdUsecase>{
        SetIdUsecase.Default(
            repository = get<StoreIdRepository>()
        )
    }
}