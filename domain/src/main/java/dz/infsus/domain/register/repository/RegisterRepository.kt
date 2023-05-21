package dz.infsus.domain.register.repository

import arrow.core.Either
import dz.infsus.utils.error.AppError
import dz.infsus.domain.register.model.LogInDetails
import dz.infsus.domain.register.model.SignUpDetails

interface RegisterRepository {
    suspend fun logIn(logInDetails: LogInDetails): Either<AppError.RegisterError, Int>

    suspend fun signUp(signUpDetails: SignUpDetails): Either<AppError.RegisterError, Int>
}