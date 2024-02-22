package com.example.homework_25_map.presentation.event

sealed class PlaceEvent {
    data class FetchPlace(val search: String? = null): PlaceEvent()
}
