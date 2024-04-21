package br.edu.projetofinalkotlin.view.weather.weather_rv

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.model.WeatherForecastResponse
import com.bumptech.glide.Glide

class WeatherViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    private val cityName: TextView = view.findViewById(R.id.city_name)
    private val cityCountry: TextView = view.findViewById(R.id.city_country)
    private val weatherIcon: ImageView = view.findViewById(R.id.weather_icon)
    private val lastUpdate: TextView = view.findViewById(R.id.last_update)
    private val temp: TextView = view.findViewById(R.id.temp)
    private val wind: TextView = view.findViewById(R.id.wind)
    private val humidity: TextView = view.findViewById(R.id.humidity)
    private val feelsLike: TextView = view.findViewById(R.id.feelsLike)

    fun bind(item: WeatherForecastResponse) {
        cityName.text = item.location.name
        cityCountry.text = item.location.country

        Glide.with(weatherIcon.context)
            .load("https:" + item.current.condition.icon)
            .into(weatherIcon)

        val lastUpdateText = "Last Update: " + item.current.last_updated
        lastUpdate.text = lastUpdateText

        val tempText = "Temperature: " + item.current.temp_c.toString()
        temp.text = tempText

        val windText = "Wind Speed: " + item.current.wind_kph.toString()
        wind.text = windText

        val humidityText = "Humidity: " + item.current.humidity.toString()
        humidity.text = humidityText

        val feelsLikeText = "Feels Like: " + item.current.feelslike_c.toString()
        feelsLike.text = feelsLikeText
    }
}
