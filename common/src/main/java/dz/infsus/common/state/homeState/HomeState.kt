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
    val startDate: String,
    val endDate: String,
    val reservationError: Boolean,
    val reservationSuccess: Boolean,
    val addNewState: AddNewState,
    val successfulAdd: Boolean,
    val deletionSuccess: Boolean,
){
    companion object{
        val Initial = HomeState(
            selectedAdvertId = 0,
            minPrice = 0.0f,
            maxPrice = 1000.0f,
            city = "",
            errorLoadingAdverts = false,
            adverts = AdvertsModel(emptyList()),
            startDate = "2023-06-01",
            endDate = "2023-06-01",
            reservationError = false,
            reservationSuccess = false,
            addNewState = AddNewState.Initial,
            successfulAdd = false,
            deletionSuccess = false,
        )
    }
}

@optics
data class AddNewState(
    val title:String,
    val description: String,
    val address: String,
    val city: String,
    val pricePerNight: Float,
    val ownerId: Int,
){
    companion object{
        val Initial = AddNewState(
            title = "",
            description = "",
            address = "",
            city = "",
            pricePerNight = 0.0f,
            ownerId = 0,
        )
    }
}
