package br.edu.projetofinalkotlin.view.weather.weather_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.model.WeatherForecastResponse

class WeatherRVAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val diffUtil = AsyncListDiffer(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WeatherViewHolder(
            inflater.inflate(R.layout.weather_rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> holder.bind(diffUtil.currentList[position])
        }
    }

    override fun getItemCount() = diffUtil.currentList.size

    fun updateList(items: List<WeatherForecastResponse>) {
        diffUtil.submitList(items)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<WeatherForecastResponse>() {
            override fun areItemsTheSame(
                oldItem: WeatherForecastResponse,
                newItem: WeatherForecastResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: WeatherForecastResponse,
                newItem: WeatherForecastResponse
            ): Boolean {
                return oldItem.location.name == newItem.location.name
            }
        }
    }

}