package com.example.homework_25_map.data.repository

import android.util.Log.d
import com.example.homework_25_map.data.common.HandleResponse
import com.example.homework_25_map.data.common.Resource
import com.example.homework_25_map.data.mapper.asResource
import com.example.homework_25_map.data.mapper.toDomain
import com.example.homework_25_map.data.service.LocationService
import com.example.homework_25_map.domain.model.GetPlaces
import com.example.homework_25_map.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val locationService: LocationService
) : LocationRepository {
    override suspend fun getLocation(search: String?): Flow<Resource<GetPlaces>> {
        return handleResponse.safeApiCall {
            locationService.getLocation(search)
        }.asResource {
            d("fetch_data", "data fetched: $it")
            it.toDomain()
        }
    }
}
