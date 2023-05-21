package dz.infsus.domain.infrastructure

import arrow.core.Either
import dz.infsus.common.error.AppError

interface QueryUseCase<out Response> {
    suspend operator fun invoke(): Either<AppError, Response>

    interface WithRequest1<in Request, out Response> {
        suspend operator fun invoke(request: Request): List<List<String>>
    }

    interface WithRequest<in Request, out Response> {
        suspend operator fun invoke(request: Request): Either<AppError, Response>
    }
}
