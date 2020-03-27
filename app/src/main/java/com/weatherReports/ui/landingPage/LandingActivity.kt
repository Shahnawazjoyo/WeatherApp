package com.weatherReports.ui.landingPage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.weatherReports.R
import com.weatherReports.base.BaseActivity
import com.weatherReports.ui.WeatherForecast.WeatherForecastActivity
import com.weatherReports.ui.weatherCurrent.WeatherActivity

class LandingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val step1Button:Button = findViewById(R.id.step1)
        val step2Button:Button = findViewById(R.id.step2)

        step1Button.setOnClickListener{
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }
        step2Button.setOnClickListener{
            val intent = Intent(this, WeatherForecastActivity::class.java)
            startActivity(intent)
        }
    }
}
