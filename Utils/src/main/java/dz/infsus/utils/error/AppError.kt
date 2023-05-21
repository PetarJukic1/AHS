package dz.infsus.utils.error

sealed interface AppError {
    data class RegisterError(val message: String = "") : AppError
    data class StoreIdError(val message: String = "") : AppError
    data class AdvertsError(val message: String = "") : AppError
}