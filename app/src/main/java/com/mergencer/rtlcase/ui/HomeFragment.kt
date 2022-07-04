package com.mergencer.rtlcase.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.snackbar.Snackbar
import com.mergencer.rtlcase.R
import com.mergencer.rtlcase.data.model.WeatherReport
import com.mergencer.rtlcase.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when(result.resultCode) {
                Activity.RESULT_OK -> {
                    // There are no request codes
                    val data: Intent? = result.data
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        viewModel.onUserPlaceSelected(place)
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    val status = Autocomplete.getStatusFromIntent(result.data!!)
                    Timber.e(status.statusMessage!!)
                    Snackbar.make(requireView(), status.statusMessage!!, Snackbar.LENGTH_LONG).show()
                }
                Activity.RESULT_CANCELED -> {
                    Snackbar.make(requireView(), getString(R.string.canceled_places_screen), Snackbar.LENGTH_LONG).show()
                }
            }
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home,
            container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = UserLocationsAdapter(UserLocationsAdapter.OnClickListener {
            navigateToDetail(it)
        }, UserLocationsAdapter.OnLongClickListener {
            showAlertDialogToDelete(it)
        })
        binding.recyclerView.adapter = adapter

        viewModel.weathersInUserPlaces.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        binding.ivAdd.setOnClickListener{
            openSearchAutocomplete()
        }

        return binding.root
    }

    private fun showAlertDialogToDelete(weatherReport: WeatherReport) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.remove))
        builder.setMessage(String.format(getString(R.string.remove_message), weatherReport.placeAddress))

        builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            dialog.dismiss()
            viewModel.removeItem(weatherReport)
        }

        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun navigateToDetail(weatherReport: WeatherReport) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                weatherReport
            )
        )
    }

    private fun openSearchAutocomplete() {
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .setTypeFilter(TypeFilter.REGIONS)
            .build(requireContext())
        resultLauncher.launch(intent)
    }
}