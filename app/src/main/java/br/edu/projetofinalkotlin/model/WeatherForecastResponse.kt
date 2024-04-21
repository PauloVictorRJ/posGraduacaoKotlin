package br.edu.projetofinalkotlin.model

data class WeatherForecastResponse(
    val location: Location,
    val current: Current,
    val air_quality: AirQuality
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val lon: Float,
    val tz_id: String,
    val localtime_epoch: Int,
    val localtime: String,
)

data class Current(
    val last_updated_epoch: Int,
    val last_updated: String,
    val temp_c: Float,
    val temp_f: Float,
    val is_day: Int,
    val condition: Condition,
    val wind_mph: Float,
    val wind_kph: Float,
    val wind_degree: Int,
    val wind_dir: String,
    val pressure_mb: Float,
    val pressure_in: Float,
    val precip_mm: Float,
    val precip_in: Float,
    val humidity: Int,
    val cloud: Int,
    val feelslike_c: Float,
    val feelslike_f: Float,
    val vis_km: Float,
    val vis_miles: Float,
    val uv: Float,
    val gust_mph: Float,
    val gust_kph: Float
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int,
)

data class AirQuality(
    val co: Float,
    val no2: Float,
    val o3: Float,
    val so2: Float,
    val pm2_5: Float,
    val pm10: Float,
)
