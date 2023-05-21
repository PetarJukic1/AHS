package dz.infsus.domain.storeId.usecase

import arrow.core.Either
import dz.infsus.common.error.AppError
import dz.infsus.domain.infrastructure.QueryUseCase
import dz.infsus.domain.storeId.repository.StoreIdRepository

interface SetIdUsecase : QueryUseCase.WithRequest<Int, Boolean>{

    class Default(
        private val repository: StoreIdRepository
    ): SetIdUsecase{
        override suspend fun invoke(request: Int): Either<AppError, Boolean> = repository.setId(request)
    }
}