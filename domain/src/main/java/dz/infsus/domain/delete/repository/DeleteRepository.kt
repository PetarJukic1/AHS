package dz.infsus.domain.delete.repository

import arrow.core.Either
import dz.infsus.utils.error.AppError

interface DeleteRepository {
    suspend fun delete(request: DeleteRequest): Either<AppError.DeleteError, Unit>
}

data class DeleteRequest(
    val advertId: Int,
    val ownerId: Int,
)