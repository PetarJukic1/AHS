package dz.infsus.domain.storeId.repository

import arrow.core.Either
import dz.infsus.common.error.AppError

interface StoreIdRepository {

    suspend fun getId(): Either<AppError.StoreIdError, Int>

    suspend fun setId(id: Int): Either<AppError.StoreIdError, Boolean>
}