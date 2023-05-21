package dz.infsus.data.register.mapper

import arrow.core.Either
import arrow.core.right
import dz.infsus.common.error.AppError
import dz.infsus.data.register.api.SingUpResponse

class SignUpMapper{
    fun toSignUpResponse(data: SingUpResponse): Int = data.userId.toInt()
}