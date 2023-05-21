package dz.infsus.domain.reservation.usecase

import arrow.core.Either
import dz.infsus.domain.infrastructure.QueryUseCase
import dz.infsus.domain.reservation.repository.ReserveRepository
import dz.infsus.domain.reservation.repository.ReserveRequest
import dz.infsus.utils.error.AppError

interface ReserveUsecase: QueryUseCase.WithRequest<ReserveRequest, Unit>{
    class Default(
        private val repository: ReserveRepository
    ): ReserveUsecase{
        override suspend fun invoke(request: ReserveRequest): Either<AppError, Unit>  = repository.reserve(request)
    }
}