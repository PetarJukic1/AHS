package dz.infsus.domain.register.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dz.infsus.domain.register.model.LogInDetails
import dz.infsus.domain.register.repository.RegisterRepository
import dz.infsus.utils.error.AppError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LogInUsecaseTest {
    private lateinit var repository: RegisterRepository
    private lateinit var useCase: LogInUsecase.Default

    @Before
    fun setup() {
        repository = mockk()
        useCase = LogInUsecase.Default(repository)
    }

    @Test
    fun `logIn should return success result`() = runBlocking {
        // Arrange
        val logInDetails = LogInDetails("username", "password")
        val expectedResponse = 0 // mock successful response
        coEvery { repository.logIn(logInDetails) } returns expectedResponse.right()

        // Act
        val result = useCase(logInDetails)

        // Assert
        assertTrue(result.isRight())
        assertEquals(expectedResponse.right(), result)
    }

    @Test
    fun `logIn should return error result`() = runBlocking {
        // Arrange
        val logInDetails = LogInDetails("username", "password")
        val expectedError = AppError.RegisterError("Unauthorized")
        coEvery { repository.logIn(logInDetails) } returns AppError.RegisterError("Unauthorized").left()

        // Act
        val result = useCase(logInDetails)

        // Assert
        assertTrue(result.isLeft())
        assertEquals(expectedError.left(), result)
    }
}
