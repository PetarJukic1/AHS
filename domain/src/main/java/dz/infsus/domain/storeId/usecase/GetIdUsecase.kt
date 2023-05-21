package dz.infsus.domain.storeId.usecase

import arrow.core.Either
import dz.infsus.utils.error.AppError
import dz.infsus.domain.infrastructure.QueryUseCase
import dz.infsus.domain.storeId.repository.StoreIdRepository

interface GetIdUsecase : QueryUseCase<Int> {

    class Default(
        private val repository: StoreIdRepository
    ): GetIdUsecase{
        override suspend fun invoke(): Either<AppError, Int> = repository.getId()
    }
}