package dz.infsus.data.storeId

import android.content.SharedPreferences
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dz.infsus.utils.error.AppError
import dz.infsus.data.infrastructure.SharedPreferencesKeys
import dz.infsus.domain.storeId.repository.StoreIdRepository

class LocalStoreIdRepository(
    private val sharedPreferences: SharedPreferences,
) : StoreIdRepository {
    override suspend fun getId(): Either<AppError.StoreIdError, Int> {
        return if (sharedPreferences.contains(SharedPreferencesKeys.ID)) {
            sharedPreferences.getInt(SharedPreferencesKeys.ID, 0).right()
        } else {
            AppError.StoreIdError().left()
        }
    }

    override suspend fun setId(id: Int): Either<AppError.StoreIdError, Boolean> {
        return if (sharedPreferences.contains(SharedPreferencesKeys.ID).not()) {
            sharedPreferences
                .edit()
                .putInt(SharedPreferencesKeys.ID, id)
                .apply()
            true.right()
        } else {
            AppError.StoreIdError().left()
        }
    }
}
