package com.example.homework_25_map.presentation.state

import com.example.homework_25_map.presentation.model.Places

data class PlaceState(
    val places: Places? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
