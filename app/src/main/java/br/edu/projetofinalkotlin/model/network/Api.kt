package br.edu.projetofinalkotlin.model.network

import br.edu.projetofinalkotlin.model.WeatherForecastResponse
import br.edu.projetofinalkotlin.model.factory.GsonFactory
import br.edu.projetofinalkotlin.model.factory.OkhttpClientFactory
import br.edu.projetofinalkotlin.model.factory.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("current.json")
    suspend fun getWeatherForecast(
        //@Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") includeAqi: String,
    ): WeatherForecastResponse

    companion object {
        val instance: Api by lazy {
            RetrofitFactory
                .build(
                    OkhttpClientFactory.build(),
                    GsonFactory.build()
                )
                .create(Api::class.java)
        }
    }
}