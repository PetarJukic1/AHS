package dz.infsus.domain.addNew.repository

import arrow.core.Either
import dz.infsus.domain.register.model.LogInDetails
import dz.infsus.domain.register.model.SignUpDetails
import dz.infsus.utils.error.AppError

interface AddNewRepository {
    suspend fun addNew(request: AddNewRequest): Either<AppError.AddNewError, Unit>
}

data class AddNewRequest(
    val title: String,
    val description: String,
    val address: String,
    val city: String,
    val pricePerNight: Float,
    val ownerId: Int,
)