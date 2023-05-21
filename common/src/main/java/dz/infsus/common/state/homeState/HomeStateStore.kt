package dz.infsus.common.state.homeState

import dz.infsus.common.state.appState.AppState
import dz.infsus.common.state.appState.homeState
import dz.infsus.common.viewstore.ViewStore
import dz.infsus.domain.adverts.usecase.GetAdvertsUsecase
import dz.infsus.domain.adverts.usecase.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewStore(
    private val getAdverts: GetAdvertsUsecase,
) : ViewStore<HomeState>(AppState.homeState) {

    private val scope = CoroutineScope(Dispatchers.Default)

    fun updateSelectedMinPrice(minPrice: Float) {
        update { state ->
            state.copy(
                minPrice = minPrice
            )
        }
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

    fun selectAdvert(advertId: Int){
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