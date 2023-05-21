package dz.infsus.common.error

sealed interface AppError {
    data class RegisterError(val message: String = "") : AppError
    data class StoreIdError(val message: String = "") : AppError
}