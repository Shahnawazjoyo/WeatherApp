package com.weatherReports.model


data class WeatherDataItem(
val clouds: Clouds,
val dt: String,
val dt_txt: String,
val main: Main,
val sys: Sys,
val weather: List<Weather>,
val wind: Wind
)