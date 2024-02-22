package com.example.homework_25_map.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlacesDto(
    @Json(name = "places")
    val places: List<PlaceDto>,
) {
    data class PlaceDto(
        @Json(name = "name")
        val name: String,
        @Json(name = "location")
        val location: DetailedLocationDto
    ) {
        data class DetailedLocationDto(
            @Json(name = "latitude")
            val latitude: Number,
            @Json(name = "longitude")
            val longitude: Number
        )
    }
}
