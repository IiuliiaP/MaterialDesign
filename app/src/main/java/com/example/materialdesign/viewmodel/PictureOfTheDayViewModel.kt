package com.example.materialdesign.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.BuildConfig

import com.example.materialdesign.model.PictureOfTheDayData
import com.example.materialdesign.model.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData(),
                               private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {
    fun getLiveData():MutableLiveData<AppState>{
        return liveData
    }

    fun sendRequest() {
        liveData.postValue(AppState.Loading)
        repositoryImpl.getPictureOfTheDayAPI().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }

    private val callback = object : Callback<PictureOfTheDayData> {
        override fun onResponse(
            call: Call<PictureOfTheDayData>,
            response: Response<PictureOfTheDayData>
        ) {
            if (response.isSuccessful) {
                liveData.postValue(AppState.Success(response.body()!!))
            } else {
                liveData.postValue(AppState.Error(IllegalStateException("error")))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayData>, t: Throwable) {

        }
    }
}
