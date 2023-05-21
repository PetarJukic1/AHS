package dz.infsus.data.register.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dz.infsus.common.error.AppError
import dz.infsus.data.register.api.LogInApi
import dz.infsus.data.register.api.LogInRequest
import dz.infsus.data.register.api.SignUpApi
import dz.infsus.data.register.api.SignUpRequest
import dz.infsus.data.register.mapper.LogInMapper
import dz.infsus.data.register.mapper.SignUpMapper
import dz.infsus.domain.register.model.LogInDetails
import dz.infsus.domain.register.model.SignUpDetails
import dz.infsus.domain.register.repository.RegisterRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.HttpException

class RemoteRegisterRepository(
    private val logInApi: LogInApi,
    private val logInMapper: LogInMapper,
    private val signUpApi: SignUpApi,
    private val signUpMapper: SignUpMapper,
) : RegisterRepository {
    override suspend fun logIn(logInDetails: LogInDetails): Either<AppError.RegisterError, Int> {
        return try {
            val response = logInApi.logIn(LogInRequest(username = logInDetails.username, password = logInDetails.password))
            logInMapper.toResponse(response).right()
        } catch (e: HttpException) {
            AppError.RegisterError(e.message ?: "").left()
        }
    }

    override suspend fun signUp(signUpDetails: SignUpDetails): Either<AppError.RegisterError, Int> {
        return try {
            val response = signUpApi.signUp(
                SignUpRequest(
                    username = signUpDetails.username,
                    password = signUpDetails.password,
                    email = signUpDetails.email,
                    phoneNumber = signUpDetails.phoneNumber
                )
            )
            return signUpMapper.toSignUpResponse(response).right()
        } catch (e: HttpException) {
            AppError.RegisterError(e.message ?: "").left()
        }
    }
}