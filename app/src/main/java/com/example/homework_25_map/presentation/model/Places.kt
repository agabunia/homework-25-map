package com.example.homework_25_map.presentation.model

data class Places(
    val places: List<Place>,
) {
    data class Place(
        val name: String,
        val location: DetailedLocation
    ) {
        data class DetailedLocation(
            val latitude: Number,
            val longitude: Number
        )
    }
}
