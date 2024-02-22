package com.example.homework_25_map.presentation.screen.bottomFragment

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_25_map.data.common.Resource
import com.example.homework_25_map.domain.repository.LocationRepository
import com.example.homework_25_map.presentation.event.PlaceEvent
import com.example.homework_25_map.presentation.mapper.toPresenter
import com.example.homework_25_map.presentation.state.PlaceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomFragmentViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    private val _placeState = MutableStateFlow(PlaceState())
    val placeState: SharedFlow<PlaceState> = _placeState.asStateFlow()

    fun onEvent(event: PlaceEvent) {
        when (event) {
            is PlaceEvent.FetchPlace -> fetchPlace(search = event.search)
        }
    }

    private fun fetchPlace(search: String?) {
        viewModelScope.launch {
            repository.getLocation(search = search).collect {
                when (it) {
                    is Resource.Success -> {
                        _placeState.update { currentState ->
                            currentState.copy(places = it.data.toPresenter())
                        }
                        d("fetching", it.data.toString())
                    }

                    is Resource.Error -> {
                        _placeState.update { currentState ->
                            currentState.copy(errorMessage = it.errorMessage)
                        }
                        d("fetching", it.errorMessage)
                    }

                    is Resource.Loading -> {
                        _placeState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

}