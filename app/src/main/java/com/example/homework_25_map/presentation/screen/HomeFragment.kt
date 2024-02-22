package com.example.homework_25_map.presentation.screen

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.example.homework_25_map.R
import com.example.homework_25_map.databinding.FragmentHomeBinding
import com.example.homework_25_map.presentation.base.BaseFragment
import com.example.homework_25_map.presentation.screen.bottomFragment.BottomFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    OnMapReadyCallback {
    private var map: GoogleMap? = null
    private var mapReady: Boolean = false
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val latitude = arguments?.getInt("latitude")?.toDouble()
    private val longitude = arguments?.getInt("longitude")?.toDouble()


    override fun bind() {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if (latitude != null && longitude != null) {
            moveMapToCurrentLocation(latitude, longitude)
        }
    }

    override fun bindListeners() {
        binding.btnLocation.setOnClickListener {
            getCurrentLocationAndMoveMap()
        }

        binding.btnSearch.setOnClickListener {
            val bottomFragment = BottomFragment()
            bottomFragment.show(parentFragmentManager, "bottom_fragment")
        }
    }

    override fun bindObserves() {
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        mapReady = true
        getCurrentLocationAndMoveMap()
    }

    private fun getCurrentLocationAndMoveMap() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
            return
        }

        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
                moveMapToCurrentLocation(currentLocation.latitude, currentLocation.longitude)
            }
        }
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 100
    }

    private fun moveMapToCurrentLocation(latitude: Double, longitude: Double) {
        val latLng = LatLng(latitude, longitude)
        val markerOptions = MarkerOptions().position(latLng).title("CurrentLocation")
        map?.clear()
        map?.addMarker(markerOptions)
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }

}