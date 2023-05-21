import com.google.gson.Gson
import dz.infsus.data.register.api.LogInApi
import dz.infsus.data.register.api.LogInRequest
import dz.infsus.data.register.api.LogInResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.HttpURLConnection

class LogInApiIntegrationTest {
    private lateinit var mockServer: MockWebServer
    private lateinit var logInApi: LogInApi

    @Before
    fun setup() {
        // Set up the MockWebServer
        mockServer = MockWebServer()
        mockServer.start()

        // Create a Retrofit instance with the MockWebServer's URL
        val retrofit = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        // Create an instance of the LogInApi using the Retrofit instance
        logInApi = retrofit.create()
    }

    @After
    fun tearDown() {
        // Shut down the MockWebServer
        mockServer.shutdown()
    }

    @Test
    fun `test logInApi logIn endpoint`() = runBlocking {
        // Define the test scenario
        val username = "testUser"
        val password = "testPassword"
        val logInRequest = LogInRequest(username, password)

        // Set up the MockWebServer's response
        val expectedResponse = LogInResponse("Success", 123)
        mockServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(Gson().toJson(expectedResponse)))

        // Execute the integration test
        val response = logInApi.logIn(logInRequest)

        // Verify the test results
        assertEquals(expectedResponse, response)
    }

    @Test(expected = HttpException::class)
    fun `test logInApi logIn endpoint with error response`(): Unit = runBlocking {
        // Define the test scenario
        val username = "testUser"
        val password = "testPassword"
        val logInRequest = LogInRequest(username, password)

        // Set up the MockWebServer's error response
        val errorResponse = MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
        mockServer.enqueue(errorResponse)

        // Execute the integration test, expect HttpException to be thrown
        logInApi.logIn(logInRequest)
    }
}
