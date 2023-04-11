package com.example.materialdesign.viewmodel

import com.example.materialdesign.model.PictureOfTheDayData

sealed class AppState {
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}