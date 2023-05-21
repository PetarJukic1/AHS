package dz.infsus.domain.delete.usecase

import arrow.core.Either
import dz.infsus.domain.delete.repository.DeleteRepository
import dz.infsus.domain.delete.repository.DeleteRequest
import dz.infsus.domain.infrastructure.QueryUseCase
import dz.infsus.domain.register.model.LogInDetails
import dz.infsus.domain.register.repository.RegisterRepository
import dz.infsus.utils.error.AppError

interface DeleteAdvertUsecase: QueryUseCase.WithRequest<DeleteRequest, Unit>{
    class Default(
        private val repository: DeleteRepository
    ): DeleteAdvertUsecase{
        override suspend fun invoke(request: DeleteRequest): Either<AppError, Unit> = repository.delete(request)
    }
}