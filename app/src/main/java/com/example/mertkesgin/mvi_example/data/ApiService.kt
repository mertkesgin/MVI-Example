package com.example.mertkesgin.mvi_example.data

import androidx.lifecycle.LiveData
import com.example.mertkesgin.mvi_example.data.model.CharacterResponse
import com.example.mertkesgin.mvi_example.data.model.LocationResponse
import com.example.mertkesgin.mvi_example.utils.ApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    fun getCharacters() : LiveData<ApiResponse<CharacterResponse>>

    @GET("location")
    fun getLocations() : LiveData<ApiResponse<LocationResponse>>
}