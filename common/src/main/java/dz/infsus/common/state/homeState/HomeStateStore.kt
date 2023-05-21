package dz.infsus.common.state.homeState

import dz.infsus.common.state.appState.AppState
import dz.infsus.common.state.appState.homeState
import dz.infsus.common.viewstore.ViewStore
import dz.infsus.domain.addNew.repository.AddNewRequest
import dz.infsus.domain.addNew.usecase.AddNewAdvertUsecase
import dz.infsus.domain.adverts.usecase.GetAdvertsUsecase
import dz.infsus.domain.adverts.usecase.Request
import dz.infsus.domain.reservation.repository.ReserveRequest
import dz.infsus.domain.reservation.usecase.ReserveUsecase
import dz.infsus.domain.storeId.usecase.GetIdUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewStore(
    private val getAdverts: GetAdvertsUsecase,
    private val getId: GetIdUsecase,
    private val makeReservation: ReserveUsecase,
    private val addNewAdvert: AddNewAdvertUsecase,
) : ViewStore<HomeState>(AppState.homeState) {

    private val scope = CoroutineScope(Dispatchers.Default)

    fun updateSelectedMinPrice(minPrice: Float) {
        update { state ->
            state.copy(
                minPrice = minPrice
            )
        }
    }

    fun updateNewAdvertTitle(title: String) {
        update { state ->
            state.copy(
                addNewState = state.addNewState.copy(
                    title = title
                )
            )
        }
    }

    fun updateNewAdvertDescription(description: String) {
        update { state ->
            state.copy(
                addNewState = state.addNewState.copy(
                    description = description
                )
            )
        }
    }

    fun updateNewAdvertAddress(address: String) {
        update { state ->
            state.copy(
                addNewState = state.addNewState.copy(
                    address = address
                )
            )
        }
    }

    fun updateNewAdvertCity(city: String) {
        update { state ->
            state.copy(
                addNewState = state.addNewState.copy(
                    city = city
                )
            )
        }
    }

    fun updateNewAdvertPricePerNight(price: String) {
        update { state ->
            state.copy(
                addNewState = state.addNewState.copy(
                    pricePerNight = price.toFloatOrNull() ?: 0.0f
                )
            )
        }
    }

    fun addNew(data: AddNewState) = scope.launch {
        val userId = getId().fold({ return@launch }, { it })
        addNewAdvert(
            AddNewRequest(
                title = data.title,
                description = data.description,
                address = data.address,
                city = data.city,
                pricePerNight = data.pricePerNight,
                ownerId = userId,
            )
        ).fold({},{
            update { state->
                state.copy(successfulAdd = true)
            }
        })
    }

    fun changeStartReservationDate(date: String) {
        update { state ->
            state.copy(
                startDate = date
            )
        }
    }

    fun changeEndReservationDate(date: String) {
        update { state ->
            state.copy(
                endDate = date
            )
        }
    }

    fun reserve(advertId: Int, startDate: String, endDate: String) = scope.launch {
        getId().fold({
            return@launch
        }, {
            makeReservation(ReserveRequest(userId = it, advertId = advertId, startDate = startDate, endDate = endDate)).fold(
                {
                    update { state ->
                        state.copy(
                            reservationError = true
                        )
                    }
                }, {
                    update { state ->
                        state.copy(
                            reservationSuccess = true
                        )
                    }
                }
            )

        })
    }

    fun updateSelectedMaxPrice(maxPrice: Float) {
        update { state ->
            state.copy(
                maxPrice = maxPrice
            )
        }
    }

    fun updateCity(city: String) {
        update { state ->
            state.copy(
                city = city
            )
        }
    }

    fun selectAdvert(advertId: Int) {
        update { state ->
            state.copy(selectedAdvertId = advertId)
        }
    }

    fun loadAdverts(
        minPrice: Float,
        maxPrice: Float,
        city: String,
    ) = scope.launch {
        val adverts = getAdverts(Request(minPrice, maxPrice, city)).fold({
            update { state ->
                state.copy(errorLoadingAdverts = true)
            }
        }, {
            update { state ->
                state.copy(adverts = it)
            }
        })
    }
}