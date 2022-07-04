package com.mergencer.rtlcase.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mergencer.rtlcase.data.model.WeatherReport
import com.mergencer.rtlcase.databinding.ItemCurrentWeatherBinding

class UserLocationsAdapter(private val onClickListener: OnClickListener, private val onLongClickListener: OnLongClickListener):
    ListAdapter<WeatherReport, UserLocationsAdapter.UserLocationsViewHolder>
        (UserLocationDiffCallback) {

    companion object UserLocationDiffCallback  : DiffUtil.ItemCallback<WeatherReport>()  {
        override fun areItemsTheSame(oldItem: WeatherReport, newItem: WeatherReport): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: WeatherReport, newItem: WeatherReport): Boolean {
            return oldItem.placeId == newItem.placeId && oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserLocationsViewHolder {
        return UserLocationsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserLocationsViewHolder, position: Int) {
        holder.bind(onClickListener, onLongClickListener, getItem(position))
    }


    class UserLocationsViewHolder private constructor(
        val binding: ItemCurrentWeatherBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(onClickListener: OnClickListener, onLongClickListener: OnLongClickListener, item: WeatherReport) {
            with(binding) {
                this.weather = item
                this.onClickListener = onClickListener
                this.onLongClickListener = onLongClickListener
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): UserLocationsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCurrentWeatherBinding.inflate(
                    layoutInflater, parent, false
                )
                return UserLocationsViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (weatherReport: WeatherReport) -> Unit) {
        fun onClick(weatherReport: WeatherReport) = clickListener(weatherReport)
    }

    class OnLongClickListener(val clickListener: (weatherReport: WeatherReport) -> Unit) {
        fun onLongClick(weatherReport: WeatherReport) = clickListener(weatherReport)
    }
}