package com.mergencer.rtlcase.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mergencer.rtlcase.R
import com.mergencer.rtlcase.data.model.WeatherReport
import com.mergencer.rtlcase.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail,
            container, false
        )

        val weatherReport = args.weatherReport

        binding.lifecycleOwner = this
        binding.weather = weatherReport
        binding.viewModel = viewModel

        val adapter = DailyTemperatureAdapter()
        binding.rvTemperature.adapter = adapter
        adapter.submitList(weatherReport.daily)

        viewModel.showAlertDialogToDelete.observe(viewLifecycleOwner) {
            it?.let {
                showAlertDialogToDelete(it)
                viewModel.doneNavigating()
            }
        }

        viewModel.navigateBack.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().popBackStack()
                viewModel.doneNavigating()
            }
        }


        return binding.root
    }


    private fun showAlertDialogToDelete(weatherReport: WeatherReport) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.remove))
        builder.setMessage(String.format(getString(R.string.remove_message), weatherReport.placeAddress))

        builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            dialog.dismiss()
            viewModel.deleteWeatherReport(weatherReport)
        }

        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}