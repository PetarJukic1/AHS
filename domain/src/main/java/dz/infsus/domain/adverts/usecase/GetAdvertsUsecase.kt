package dz.infsus.domain.adverts.usecase

import arrow.core.Either
import dz.infsus.domain.adverts.model.AdvertsModel
import dz.infsus.domain.adverts.repository.AdvertsRepository
import dz.infsus.domain.infrastructure.QueryUseCase
import dz.infsus.utils.error.AppError

interface GetAdvertsUsecase : QueryUseCase.WithRequest<Request, AdvertsModel> {
    class Default(
        private val repository: AdvertsRepository
    ) : GetAdvertsUsecase {
        override suspend fun invoke(request: Request): Either<AppError, AdvertsModel> = repository.getAdverts(request)
    }
}

data class Request(
    val minPrice: Float,
    val maxPrice: Float,
    val city: String,
)