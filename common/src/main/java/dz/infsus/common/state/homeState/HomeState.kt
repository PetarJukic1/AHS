package dz.infsus.common.state.homeState

import arrow.optics.optics
import dz.infsus.domain.adverts.model.AdvertsModel

@optics
data class HomeState(
    val selectedAdvertId: Int,
    val minPrice: Float,
    val maxPrice: Float,
    val city: String,
    val errorLoadingAdverts: Boolean,
    val adverts: AdvertsModel,
){
    companion object{
        val Initial = HomeState(
            selectedAdvertId = 0,
            minPrice = 0.0f,
            maxPrice = 1000.0f,
            city = "",
            errorLoadingAdverts = false,
            adverts = AdvertsModel(emptyList())
        )
    }
}
