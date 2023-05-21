package dz.infsus.domain.adverts.repository

import arrow.core.Either
import dz.infsus.domain.adverts.model.AdvertsModel
import dz.infsus.domain.adverts.usecase.Request
import dz.infsus.utils.error.AppError

interface AdvertsRepository{
    suspend fun getAdverts(request: Request): Either<AppError.AdvertsError, AdvertsModel>
}
