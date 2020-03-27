package com.weatherReports.ui.WeatherForecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.weatherReports.R
import com.weatherReports.utils.ErrorHandlingClass

class WeatherForecastActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModelWeatherForecast
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Weather Forecast"

        inIt()
        myObservers()
        getWeatherForecast()
    }

    private fun inIt() {
        listView = findViewById<ListView>(R.id.recipe_list_view)
        viewModel = ViewModelProviders.of(this).get(ViewModelWeatherForecast::class.java)
    }

    private fun myObservers() {

        viewModel.mResponseWeatherForecast.observe(this, Observer {

            val adapter = WeatherAdapter(this, it.list)
            listView.adapter = adapter
            Toast.makeText(this, "Forecast:"+it.cod, Toast.LENGTH_SHORT).show()
        })
        viewModel.throwable.observe(this, Observer {
            ErrorHandlingClass.errorHandlingException(this, it)
        })

    }

    private fun getWeatherForecast() {
        viewModel.getWeatherForecast("Dubai")
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

}
