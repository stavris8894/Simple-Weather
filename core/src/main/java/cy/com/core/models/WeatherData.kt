package cy.com.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import cy.com.core.room.WeatherConverter

@Entity
data class WeatherData(
    @SerializedName("app_temp")
    val apparentTemperature: Double,
    @SerializedName("aqi")
    val airQualityIndex: Double,
    @SerializedName("city_name")
    @PrimaryKey
    val cityName: String,
    @SerializedName("clouds")
    val clouds: Double,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("datetime")
    val dateTime: String,
    @SerializedName("dewpt")
    val dewPoint: Double,
    @SerializedName("dhi")
    val diffuseHorizontalSolar: Double,
    @SerializedName("dni")
    val directNormalSolar: Double,
    @SerializedName("elev_angle")
    val solarElevationAngle: Double,
    @SerializedName("ghi")
    val globalHorizontalSolarIrradiance: Double,
    @SerializedName("h_angle")
    val solarHourAngle: Double,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lon")
    val lon: String,
    @SerializedName("ob_time")
    val lastObservationTimeString: String,
    @SerializedName("pod")
    val partOfTheDay: String,
    @SerializedName("precip")
    val liquidEquivalentPrecipitationRate: Double,
    @SerializedName("pres")
    val pressure: Double,
    @SerializedName("rh")
    val relativeHumidity: Double,
    @SerializedName("slp")
    val seaLevelPressure: Double,
    @SerializedName("solar_rad")
    val solarRadiation: Double,
    @SerializedName("state_code")
    val stateCode: String,
    @SerializedName("station")
    val station: String,
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("ts")
    val lastObservationTimeTimestamp: Double,
    @SerializedName("uv")
    val uv: Double,
    @SerializedName("vis")
    val visibility: Double,
    @SerializedName("weather")
    @TypeConverters(WeatherConverter::class)
    val weather: Weather,
    @SerializedName("wind_cdir")
    val windDirection: String,
    @SerializedName("wind_cdir_full")
    val windDirectionFull: String,
    @SerializedName("wind_dir")
    val windDirectionDegrees: Double,
    @SerializedName("wind_spd")
    val windSpeed: Double
)