package com.example.mertkesgin.mvi_example.data

import androidx.lifecycle.LiveData
import com.example.mertkesgin.mvi_example.data.model.Character
import com.example.mertkesgin.mvi_example.data.model.Location
import com.example.mertkesgin.mvi_example.utils.ApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/character")
    fun getCharacters() : LiveData<ApiResponse<List<Character>>>

    @GET("/location")
    fun getLocations() : LiveData<ApiResponse<List<Location>>>
}