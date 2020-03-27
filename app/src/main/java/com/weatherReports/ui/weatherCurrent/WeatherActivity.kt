package com.weatherReports.ui.weatherCurrent

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.weatherReports.R
import com.weatherReports.utils.ErrorHandlingClass

class WeatherActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModelWeather
    private lateinit var tvTemp: TextView;
    private lateinit var tvTempMin: TextView;
    private lateinit var tvTempMax: TextView;
    private lateinit var tvTempWind: TextView;
    private lateinit var tvdescription: TextView;
    private lateinit var tvCity: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Current Weather"

        inIt()
        myObservers()

    }


    private fun inIt() {
        val etCities:EditText =findViewById(R.id.et_cityNames);
        val getWeatherBt:Button=findViewById(R.id.getWeatherBt);
        getWeatherBt.setOnClickListener{

            if(validateCityNames(etCities.text.toString())){
                getWeathers(etCities.text.toString())
            }else{
                Toast.makeText(this, "Enter Minimum 3 City Name Followed by comma", Toast.LENGTH_LONG).show()

            }

        }

        tvTemp = findViewById(R.id.tv_current_temp);
        tvCity = findViewById(R.id.tv_current_city);
        tvTempMin = findViewById(R.id.tempMin);
        tvTempMax = findViewById(R.id.tempMax);
        tvTempWind = findViewById(R.id.windSpeed);
        tvdescription = findViewById(R.id.description);
        viewModel = ViewModelProviders.of(this).get(ViewModelWeather::class.java)

    }

    private fun myObservers() {
        viewModel.mResponseWeather.observe(this, Observer {
            tvTemp.text = it.main.temp
            tvCity.text = it.name
            tvTempMin.text = it.main.temp_min
            tvTempMax.text = it.main.temp_max
            tvTempWind.text = it.wind.speed.toString()
            tvdescription.text = it.weather[0].description

            Toast.makeText(this, "current:"+it.name, Toast.LENGTH_LONG).show()
            //viewModel.getWeatherForecast("Dubai,Abu Dhabi, Sharjah")

        })

        viewModel.mResponseWeatherForecast.observe(this, Observer {
            //Toast.makeText(this, "Forecast:"+it.cod, Toast.LENGTH_SHORT).show()
            //openBottomSheet()
        })
        viewModel.throwable.observe(this, Observer {
            ErrorHandlingClass.errorHandlingException(this, it)
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }


    private fun getWeathers(cityNames:String) {
        viewModel.getWeatherCurrent(cityNames)
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return super.onNavigateUp()
    }

    fun validateCityNames(cityNames:String): Boolean {
        return if(cityNames == ""){
            false;
        }else{
            val citiesArray = cityNames.split(",").toTypedArray()
            citiesArray.size in 2..7
        }
    }
}
