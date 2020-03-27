package com.weatherReports.ui.WeatherForecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.weatherReports.R
import com.weatherReports.model.WeatherDataItem

class WeatherAdapter(private val context: Context,
                     private val dataSource: ArrayList<WeatherDataItem>): BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.item_waether_forecast, parent, false)

        // Get tvDate element
        val tvDate = rowView.findViewById(R.id.tvDate) as TextView

        // Get tempMin element
        val tempMin = rowView.findViewById(R.id.tvTempMin) as TextView

        // Get tempMax element
        val tempMax = rowView.findViewById(R.id.tvTempMax) as TextView

        // Get tvDescription element
        val tvDescription = rowView.findViewById(R.id.tvDescription) as TextView

        // Get windSpeed element
        val windSpeed = rowView.findViewById(R.id.tvWindSpeed) as TextView


        val recipe = getItem(position) as WeatherDataItem

        //set Values to List elements
        tvDate.text = "Date: "+recipe.dt_txt
        tempMin.text = "Temp Min:"+recipe.main.temp_min
        tempMax.text = "Temp Max:"+recipe.main.temp_max
        tvDescription.text ="Wind Speed: "+ recipe.wind.speed.toString()
        windSpeed.text = "Description: "+recipe.weather[0].description


        return rowView
    }


}