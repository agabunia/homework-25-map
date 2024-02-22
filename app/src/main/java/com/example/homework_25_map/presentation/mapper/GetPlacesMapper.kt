package com.example.homework_25_map.presentation.mapper

import com.example.homework_25_map.domain.model.GetPlaces
import com.example.homework_25_map.presentation.model.Places

fun GetPlaces.toPresenter(): Places {
    val getPlaceList = places.map { placeDto ->
        Places.Place(
            name = placeDto.name,
            location = Places.Place.DetailedLocation(
                latitude = placeDto.location.latitude,
                longitude = placeDto.location.longitude
            )
        )
    }
    return Places(places = getPlaceList)
}
