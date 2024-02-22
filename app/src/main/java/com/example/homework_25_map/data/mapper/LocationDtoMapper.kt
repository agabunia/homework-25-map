package com.example.homework_25_map.data.mapper

import com.example.homework_25_map.data.model.PlacesDto
import com.example.homework_25_map.domain.model.GetPlaces

fun PlacesDto.toDomain(): GetPlaces {
    val getPlaceList = places.map { placeDto ->
        GetPlaces.GetPlace(
            name = placeDto.name,
            location = GetPlaces.GetPlace.GetDetailedLocation(
                latitude = placeDto.location.latitude,
                longitude = placeDto.location.longitude
            )
        )
    }
    return GetPlaces(places = getPlaceList)
}
