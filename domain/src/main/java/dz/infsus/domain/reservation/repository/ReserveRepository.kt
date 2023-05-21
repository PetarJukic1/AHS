package dz.infsus.domain.reservation.repository

import arrow.core.Either
import dz.infsus.utils.error.AppError

interface ReserveRepository{
    suspend fun reserve(request: ReserveRequest): Either<AppError.ReservationError, Unit>
}

data class ReserveRequest(
    val userId: Int,
    val advertId: Int,
    val startDate: String,
    val endDate: String,
)