package com.example.homework_25_map.di

import com.example.homework_25_map.data.common.HandleResponse
import com.example.homework_25_map.data.repository.LocationRepositoryImpl
import com.example.homework_25_map.data.service.LocationService
import com.example.homework_25_map.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocationRepository(
        handleResponse: HandleResponse,
        locationService: LocationService
    ): LocationRepository {
        return LocationRepositoryImpl(
            handleResponse = handleResponse,
            locationService = locationService
        )
    }

}