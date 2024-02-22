package com.example.homework_25_map.domain.repository

import com.example.homework_25_map.data.common.Resource
import com.example.homework_25_map.domain.model.GetPlaces
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocation(search: String? = null): Flow<Resource<GetPlaces>>
}
