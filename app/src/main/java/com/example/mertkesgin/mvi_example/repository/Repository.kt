package com.example.mertkesgin.mvi_example.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mertkesgin.mvi_example.data.ApiService
import com.example.mertkesgin.mvi_example.data.RetrofitInstance
import com.example.mertkesgin.mvi_example.ui.main.state.MainViewState
import com.example.mertkesgin.mvi_example.utils.ApiEmptyResponse
import com.example.mertkesgin.mvi_example.utils.ApiErrorResponse
import com.example.mertkesgin.mvi_example.utils.ApiSuccessResponse

class Repository {

    fun getCharacters(): LiveData<MainViewState>{
        return Transformations.switchMap(
                RetrofitInstance()
                        .buildApi(ApiService::class.java)
                        .getCharacters()
        ){ apiResponse ->
            object : LiveData<MainViewState>(){
                override fun onActive() {
                    super.onActive()
                    when(apiResponse){
                        is ApiSuccessResponse -> {
                            value = MainViewState(
                                    characterList =  apiResponse.body.results
                            )
                        }
                        is ApiErrorResponse -> {
                            value = MainViewState()
                        }
                        is ApiEmptyResponse -> {
                            value = MainViewState()
                        }
                    }
                }
            }
        }
    }

    fun getLocations(): LiveData<MainViewState>{
        return Transformations.switchMap(
                RetrofitInstance()
                        .buildApi(ApiService::class.java)
                        .getLocations()
        ){ apiResponse ->
            object : LiveData<MainViewState>(){
                override fun onActive() {
                    super.onActive()
                    when(apiResponse){
                        is ApiSuccessResponse -> {
                            value = MainViewState(
                                    locationList = apiResponse.body.results
                            )
                        }
                        is ApiErrorResponse -> {
                            value = MainViewState()
                        }
                        is ApiEmptyResponse -> {
                            value = MainViewState()
                        }
                    }
                }
            }
        }
    }
}