package dz.infsus.data.adverts.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dz.infsus.data.adverts.api.AdvertsApi
import dz.infsus.data.adverts.mapper.AdvertsMapper
import dz.infsus.domain.adverts.model.AdvertsModel
import dz.infsus.domain.adverts.repository.AdvertsRepository
import dz.infsus.domain.adverts.usecase.Request
import dz.infsus.utils.error.AppError
import retrofit2.HttpException

class RemoteAdvertsRepository(
    private val advertsApi: AdvertsApi,
    private val mapper: AdvertsMapper,
) : AdvertsRepository {
    override suspend fun getAdverts(request: Request): Either<AppError.AdvertsError, AdvertsModel> {
        return try {
            mapper.toResponse(
                advertsApi.getAdverts(
                    minPrice = request.minPrice,
                    maxPrice = request.maxPrice,
                    city = request.city
                )
            ).right()
        } catch (e: HttpException) {
            AppError.AdvertsError().left()
        }
    }
}
