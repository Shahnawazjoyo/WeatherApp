package com.weatherReports.ui.WeatherForecast


import androidx.lifecycle.MutableLiveData

import com.weatherReports.base.BaseViewModel

import com.weatherReports.model.ResponseWeatherForecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ViewModelWeatherForecast() : BaseViewModel() {

    private var dis: Disposable? = null
    var throwable = MutableLiveData<Throwable>()
    var mResponseWeatherForecast = MutableLiveData<ResponseWeatherForecast>()

    fun getWeatherForecast(city: String) {

        dis = apiInterface.weatherForecastApi(q = city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccessForecast(it) },
                { onError(it) }
            )
    }

    private fun onSuccessForecast(it: ResponseWeatherForecast?) {
        mResponseWeatherForecast.value=it
    }


    private fun onError(it: Throwable) {
        throwable.value = it
    }
}