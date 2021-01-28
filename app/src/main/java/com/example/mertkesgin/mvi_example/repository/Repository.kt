package com.example.mertkesgin.mvi_example.repository

import androidx.lifecycle.LiveData
import com.example.mertkesgin.mvi_example.data.ApiService
import com.example.mertkesgin.mvi_example.data.RetrofitInstance
import com.example.mertkesgin.mvi_example.data.model.CharacterResponse
import com.example.mertkesgin.mvi_example.data.model.LocationResponse
import com.example.mertkesgin.mvi_example.ui.main.state.MainViewState
import com.example.mertkesgin.mvi_example.utils.*

class Repository {

    fun getCharacters(): LiveData<DataState<MainViewState>>{
        return object : NetworkBoundResource<CharacterResponse,MainViewState>(){

            override fun createApiCall(): LiveData<ApiResponse<CharacterResponse>> {
                return RetrofitInstance()
                        .buildApi(ApiService::class.java)
                        .getCharacters()
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CharacterResponse>) {
                result.value = DataState.data(
                        null,
                        data = MainViewState(
                                characterList = response.body.results
                        )
                )
            }

        }.asLiveData()
    }

    fun getLocations(): LiveData<DataState<MainViewState>>{
        return object : NetworkBoundResource<LocationResponse,MainViewState>(){

            override fun createApiCall(): LiveData<ApiResponse<LocationResponse>> {
                return RetrofitInstance()
                        .buildApi(ApiService::class.java)
                        .getLocations()
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<LocationResponse>) {
                result.value = DataState.data(
                        null,
                        data = MainViewState(
                                locationList = response.body.results
                        )
                )
            }

        }.asLiveData()
    }
}