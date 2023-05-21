package dz.infsus.domain.register.usecase

import dz.infsus.domain.infrastructure.QueryUseCase
import dz.infsus.domain.register.model.SignUpDetails
import dz.infsus.domain.register.repository.RegisterRepository

interface SignUpUsecase: QueryUseCase.WithRequest<SignUpDetails, Int>{
    class Default(
        private val repository: RegisterRepository
    ): SignUpUsecase{
        override suspend fun invoke(request: SignUpDetails) = repository.signUp(request)
    }
}