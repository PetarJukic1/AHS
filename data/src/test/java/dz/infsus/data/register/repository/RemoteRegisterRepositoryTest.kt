package dz.infsus.data.register.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dz.infsus.data.register.api.LogInApi
import dz.infsus.data.register.api.LogInRequest
import dz.infsus.data.register.api.LogInResponse
import dz.infsus.data.register.api.SignUpApi
import dz.infsus.data.register.api.SingUpResponse
import dz.infsus.data.register.mapper.LogInMapper
import dz.infsus.data.register.mapper.SignUpMapper
import dz.infsus.domain.register.model.LogInDetails
import dz.infsus.domain.register.model.SignUpDetails
import dz.infsus.utils.error.AppError
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class RemoteRegisterRepositoryTest {
    private val logInApi: LogInApi = mockk()
    private val logInMapper: LogInMapper = mockk()
    private val signUpApi: SignUpApi = mockk()
    private val signUpMapper: SignUpMapper = mockk()

    private val sut: RemoteRegisterRepository = RemoteRegisterRepository(
        logInApi = logInApi,
        logInMapper = logInMapper,
        signUpApi = signUpApi,
        signUpMapper = signUpMapper,
    )

    private val logInRequest = LogInRequest(
        username = "test",
        password = "test"
    )

    private val logInResponse = LogInResponse(
        message = "test",
        userId = 0
    )

    private val signUpResponse = SingUpResponse(
        message = "test",
        userId = 0
    )

    @Test
    fun `logIn should return error if api throws exception`() = runBlocking {
        // Arrange
        val logInDetails = LogInDetails("username", "password")
        val response = 200 // mock successful response
        val errorResponse = HttpException(Response.error<LogInResponse>(402, "".toResponseBody("application/json".toMediaTypeOrNull())))
        coEvery { logInApi.logIn(any()) } throws  errorResponse
        coEvery { logInMapper.toResponse(any()) } returns response

        // Act
        val result = sut.logIn(logInDetails)

        // Assert
        assertTrue(result.isLeft())
    }


    @Test
    fun `logIn should return mapped response if there is no exception`() = runBlocking {
        // Arrange
        val logInDetails = LogInDetails("username", "password")

        coEvery { logInApi.logIn(any()) } returns  logInResponse
        coEvery { logInMapper.toResponse(logInResponse) } returns 0
        // Act
        val result = sut.logIn(logInDetails)

        // Assert
        assertTrue(result.isRight())
    }

    @Test
    fun `sign Up should return mapped response if there is no exception`() = runBlocking {
        // Arrange
        val signUpDetails = SignUpDetails("username", "email","password", "000000")

        coEvery { signUpApi.signUp(any()) } returns  signUpResponse
        coEvery { signUpMapper.toSignUpResponse(signUpResponse) } returns 0
        // Act
        val result = sut.signUp(signUpDetails)

        // Assert
        assertTrue(result.isRight())
    }

    @Test
    fun `signUp should return error if api throws exception`() = runBlocking {
        // Arrange
        val signUpDetails = SignUpDetails("username", "email","password", "000000")
        val errorResponse = HttpException(Response.error<LogInResponse>(402, "".toResponseBody("application/json".toMediaTypeOrNull())))
        coEvery { signUpApi.signUp(any()) } throws  errorResponse
        coEvery { signUpMapper.toSignUpResponse(any()) } returns 0

        // Act
        val result = sut.signUp(signUpDetails)

        // Assert
        assertTrue(result.isLeft())
    }
}