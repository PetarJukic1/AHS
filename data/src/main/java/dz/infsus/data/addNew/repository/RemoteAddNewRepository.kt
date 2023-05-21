package dz.infsus.data.addNew.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dz.infsus.data.addNew.api.AddNewApi
import dz.infsus.data.addNew.api.AddNewRequestObject
import dz.infsus.domain.addNew.repository.AddNewRepository
import dz.infsus.domain.addNew.repository.AddNewRequest
import dz.infsus.utils.error.AppError
import retrofit2.HttpException

class RemoteAddNewRepository(
    private val addNewApi: AddNewApi,
) : AddNewRepository {
    override suspend fun addNew(request: AddNewRequest): Either<AppError.AddNewError, Unit> {
        return try {
            addNewApi.addNew(
                AddNewRequestObject(
                    title = request.title,
                    description = request.description,
                    address = request.address,
                    city = request.city,
                    price_per_night = request.pricePerNight,
                    ownerId = request.ownerId
                )
            ).right()
        } catch (e: HttpException) {
            AppError.AddNewError(e.message ?: "").left()
        }
    }
}