package dz.infsus.data.register.mapper

import dz.infsus.data.register.api.SingUpResponse

class SignUpMapper{
    fun toSignUpResponse(data: SingUpResponse): Int = data.userId.toInt()
}