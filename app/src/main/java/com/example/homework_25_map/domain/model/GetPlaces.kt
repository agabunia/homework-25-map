package com.example.homework_25_map.domain.model

data class GetPlaces(
    val places: List<GetPlace>,
) {
    data class GetPlace(
        val name: String,
        val location: GetDetailedLocation
    ) {
        data class GetDetailedLocation(
            val latitude: Number,
            val longitude: Number
        )
    }
}
