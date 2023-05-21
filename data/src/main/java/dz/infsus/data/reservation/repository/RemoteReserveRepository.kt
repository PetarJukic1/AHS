package dz.infsus.data.reservation.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dz.infsus.data.register.api.LogInApi
import dz.infsus.data.register.api.SignUpApi
import dz.infsus.data.register.mapper.LogInMapper
import dz.infsus.data.register.mapper.SignUpMapper
import dz.infsus.data.reservation.api.ReservationApi
import dz.infsus.data.reservation.api.ReservationRequest
import dz.infsus.domain.register.repository.RegisterRepository
import dz.infsus.domain.reservation.repository.ReserveRepository
import dz.infsus.domain.reservation.repository.ReserveRequest
import dz.infsus.utils.error.AppError
import retrofit2.HttpException

class RemoteReserveRepository(
    private val reservationApi: ReservationApi,
) : ReserveRepository {
    override suspend fun reserve(request: ReserveRequest): Either<AppError.ReservationError, Unit> {
        return try {
            reservationApi.reserve(
                reservationBody = ReservationRequest(
                    userId = request.userId,
                    advertId = request.advertId,
                    startDate = request.startDate,
                    endDate = request.endDate
                )
            ).right()
        } catch (e: HttpException) {
            AppError.ReservationError(e.message()).left()
        }

    }
}