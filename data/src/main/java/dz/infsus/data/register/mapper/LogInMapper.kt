package dz.infsus.data.register.mapper

import dz.infsus.data.register.api.LogInResponse

class LogInMapper{
    fun toResponse(data: LogInResponse): Int = data.userId
}