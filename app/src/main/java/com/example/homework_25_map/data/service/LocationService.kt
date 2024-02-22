package com.example.homework_25_map.data.service

import com.example.homework_25_map.R
import com.example.homework_25_map.data.model.PlacesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface LocationService {
    @GET("https://places.googleapis.com/v1/places:{searchText}")
    @Headers(
        "Content-Type: application/json",
        "X-Goog-Api-Key: AIzaSyCwKeskiHciGSYlYAzlLQPT0-ZoYE5ROJg",
        "X-Goog-FieldMask: places.name, places.location"
    )
    suspend fun getLocation(@Path("searchText") search: String?): Response<PlacesDto>
}
