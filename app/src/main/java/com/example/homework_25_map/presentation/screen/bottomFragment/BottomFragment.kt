package com.example.homework_25_map.presentation.screen.bottomFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework_25_map.databinding.FragmentBottomBinding
import com.example.homework_25_map.presentation.event.PlaceEvent
import com.example.homework_25_map.presentation.state.PlaceState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BottomFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BottomFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUp() {
        listener()
        bindObservers()
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.placeState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun listener() {
        binding.btnSearch.setOnClickListener {
            val search = binding.etSearchBar.text.toString()
            if(search.isNotEmpty()) {
                viewModel.onEvent(PlaceEvent.FetchPlace(search))
            }
            dismiss()
        }
    }

    private fun handleState(state: PlaceState) {
        state.places?.let {
            val bundle = Bundle()
            val latitude = it.places.first().location.latitude.toInt()
            val longitude = it.places.first().location.longitude.toInt()
            bundle.putInt("latitude", latitude)
            bundle.putInt("longitude", longitude)
        }

        state.errorMessage?.let {
            toastMessage(it)
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}