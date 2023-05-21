package dz.infsus.domain.addNew.usecase

import arrow.core.Either
import dz.infsus.domain.addNew.repository.AddNewRepository
import dz.infsus.domain.addNew.repository.AddNewRequest
import dz.infsus.domain.infrastructure.QueryUseCase
import dz.infsus.domain.register.model.LogInDetails
import dz.infsus.domain.register.repository.RegisterRepository
import dz.infsus.domain.register.usecase.LogInUsecase
import dz.infsus.utils.error.AppError

interface AddNewAdvertUsecase : QueryUseCase.WithRequest<AddNewRequest, Unit> {
    class Default(
        private val repository: AddNewRepository
    ) : AddNewAdvertUsecase {
        override suspend fun invoke(request: AddNewRequest): Either<AppError, Unit> = repository.addNew(request)
    }
}