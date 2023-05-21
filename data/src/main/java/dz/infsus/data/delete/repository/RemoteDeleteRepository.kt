package dz.infsus.data.delete.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dz.infsus.data.delete.api.DeleteApi
import dz.infsus.data.delete.api.DeleteRequestBody
import dz.infsus.domain.delete.repository.DeleteRepository
import dz.infsus.domain.delete.repository.DeleteRequest
import dz.infsus.utils.error.AppError
import retrofit2.HttpException

class RemoteDeleteRepository(
    private val deleteApi: DeleteApi,
) : DeleteRepository {
    override suspend fun delete(request: DeleteRequest): Either<AppError.DeleteError, Unit> {
        return try {
            deleteApi.deleteAdvert(
                deleteRequest = DeleteRequestBody(
                    id = request.advertId,
                    ownerId = request.ownerId
                )
            ).right()
        } catch (e: HttpException) {
            AppError.DeleteError(e.message()).left()
        }
    }
}