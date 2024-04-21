package br.edu.projetofinalkotlin.model.repository.weather

import br.edu.projetofinalkotlin.model.WeatherForecastResponse
import br.edu.projetofinalkotlin.model.network.Api
import br.edu.projetofinalkotlin.util.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepository(
    private val api: Api = Api.instance
) {
    fun getWeatherForecastRepository(): Flow<DataResult<List<WeatherForecastResponse>>> =
        flow {
            val locations = listOf("Rio de Janeiro", "Tokyo", "Rome", "Manila", "Brasov", "Antananarivo")
            val weatherForecastList = locations.map { location ->
                api.getWeatherForecast(location, "yes")
            }
            emit(DataResult.Success(weatherForecastList))
        }.flowOn(Dispatchers.IO)

    companion object {
        val instance: WeatherRepository by lazy { WeatherRepository() }
    }
}