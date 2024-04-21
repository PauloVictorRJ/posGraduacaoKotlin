package br.edu.projetofinalkotlin.viewmodel.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.edu.projetofinalkotlin.model.WeatherForecastResponse
import br.edu.projetofinalkotlin.model.repository.weather.WeatherRepository
import br.edu.projetofinalkotlin.util.DataResult

class WeatherViewModel(private val repository: WeatherRepository = WeatherRepository.instance) :
    ViewModel() {

    fun getWeatherForecastViewModel(): LiveData<DataResult<List<WeatherForecastResponse>>> =
        repository.getWeatherForecastRepository().asLiveData()
}
