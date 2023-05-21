package dz.infsus.common.state.appAccessState

import dz.infsus.common.state.appState.AppState
import dz.infsus.common.state.appState.appAccessState
import dz.infsus.common.viewstore.ViewStore
import dz.infsus.domain.register.model.LogInDetails
import dz.infsus.domain.register.model.SignUpDetails
import dz.infsus.domain.register.usecase.LogInUsecase
import dz.infsus.domain.register.usecase.SignUpUsecase
import dz.infsus.domain.storeId.usecase.GetIdUsecase
import dz.infsus.domain.storeId.usecase.SetIdUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class AppAccessViewStore(
    private val logIn: LogInUsecase,
    private val signUp: SignUpUsecase,
    private val setId: SetIdUsecase,
    private val getId: GetIdUsecase,
) : ViewStore<AppAccessState>(AppState.appAccessState) {

    private val scope = CoroutineScope(Dispatchers.Default)

    fun updateUsername(username: String) {
        update { state ->
            state.copy(
                username = username
            )
        }
    }

    fun isRegistered() = scope.launch {
        getId().fold(
            {
                update { state ->
                    state.copy(appAccessSuccess = false)
                }
            }, {
                update { state ->
                    state.copy(appAccessSuccess = true)
                }
            }
        )
    }

    fun updatePassword(password: String) {
        update { state ->
            state.copy(
                password = password
            )
        }
    }

    fun updateEmail(email: String) {
        update { state ->
            state.copy(
                email = email
            )
        }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        update { state ->
            state.copy(
                phoneNumber = phoneNumber
            )
        }
    }

    fun logInUser(username: String, password: String) = scope.launch {
        logIn(
            LogInDetails(
                username = username,
                password = getHash(password)
            )
        ).fold({
            update { state ->
                state.copy(isError = true)
            }
        }, {
            update { state ->
                state.copy(appAccessSuccess = true)
            }
        })
    }

    fun signUpUser(username: String, password: String, email: String, phoneNumber: String) = scope.launch {
        signUp(
            SignUpDetails(
                username = username,
                password = getHash(password),
                email = email,
                phoneNumber = phoneNumber,
            )
        ).fold({
            update { state ->
                state.copy(isError = true)
            }
        }, {
            update { state ->
                state.copy(appAccessSuccess = true)
            }
            println("[test] here")
            setId(it)
        })
    }
}

fun getHash(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}