package dz.infsus.domain.register.usecase

import dz.infsus.domain.infrastructure.QueryUseCase
import dz.infsus.domain.register.model.LogInDetails
import dz.infsus.domain.register.repository.RegisterRepository

interface LogInUsecase: QueryUseCase.WithRequest<LogInDetails, Int>{
    class Default(
        private val repository: RegisterRepository
    ): LogInUsecase{
        override suspend fun invoke(request: LogInDetails) = repository.logIn(request)
    }
}