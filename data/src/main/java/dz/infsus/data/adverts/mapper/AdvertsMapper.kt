package dz.infsus.data.adverts.mapper

import dz.infsus.data.adverts.api.Advert
import dz.infsus.data.adverts.api.Reservation
import dz.infsus.domain.adverts.model.AdvertData
import dz.infsus.domain.adverts.model.AdvertsModel
import dz.infsus.domain.adverts.model.ReservationModel

class AdvertsMapper{
    fun toResponse(data: List<Advert>) = AdvertsModel(
        adverts = mapAdverts(data)
    )

    private fun mapAdverts(data: List<Advert>) = data.map { advert ->
        AdvertData(
            id = advert.id,
            title = advert.title,
            description = advert.description,
            pictures = advert.pictures.map { "http://vulama.ddns.net:4321/images/${it}" },
            address = advert.address,
            city = advert.city,
            pricePerNight = advert.pricePerNight,
            username = advert.username,
            phoneNumber = advert.phoneNumber,
            email = advert.email,
            reservations = mapReservations(advert.reservations)
        )
    }

    private fun mapReservations(reservations: List<Reservation>) = reservations.map { reservation ->
        ReservationModel(
            id = reservation.id,
            startDate = reservation.startDate.split('T')[0],
            endDate = reservation.endDate.split('T')[0],
            userId = reservation.userId,
            advertId = reservation.advertId
        )
    }
}