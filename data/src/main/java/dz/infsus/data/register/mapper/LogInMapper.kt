package dz.infsus.data.register.mapper

import arrow.core.Either
import arrow.core.right
import dz.infsus.common.error.AppError
import dz.infsus.data.register.api.LogInApi
import dz.infsus.data.register.api.LogInResponse

class LogInMapper{
    fun toResponse(data: LogInResponse): Int = data.userId
}