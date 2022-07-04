package com.mergencer.rtlcase.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mergencer.rtlcase.data.model.Weather
import com.mergencer.rtlcase.databinding.ItemDailyTemperatureBinding

class DailyTemperatureAdapter:
    ListAdapter<Weather, DailyTemperatureAdapter.DailyTemperatureViewHolder>
        (UserLocationDiffCallback) {

    companion object UserLocationDiffCallback  : DiffUtil.ItemCallback<Weather>()  {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.currentTime == newItem.currentTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyTemperatureViewHolder {
        return DailyTemperatureViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DailyTemperatureViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DailyTemperatureViewHolder private constructor(
        val binding: ItemDailyTemperatureBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Weather) {
            with(binding) {
                this.weather = item
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): DailyTemperatureViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDailyTemperatureBinding.inflate(
                    layoutInflater, parent, false
                )
                return DailyTemperatureViewHolder(binding)
            }
        }
    }
}