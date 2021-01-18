package com.example.mertkesgin.mvi_example.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mertkesgin.mvi_example.data.ApiService
import com.example.mertkesgin.mvi_example.data.RetrofitInstance
import com.example.mertkesgin.mvi_example.ui.main.state.MainViewState
import com.example.mertkesgin.mvi_example.utils.ApiEmptyResponse
import com.example.mertkesgin.mvi_example.utils.ApiErrorResponse
import com.example.mertkesgin.mvi_example.utils.ApiSuccessResponse
import com.example.mertkesgin.mvi_example.utils.DataState

class Repository {

    fun getCharacters(): LiveData<DataState<MainViewState>>{
        return Transformations.switchMap(
                RetrofitInstance()
                        .buildApi(ApiService::class.java)
                        .getCharacters()
        ){ apiResponse ->
            object : LiveData<DataState<MainViewState>>(){
                override fun onActive() {
                    super.onActive()
                    when(apiResponse){
                        is ApiSuccessResponse -> {
                            value = DataState.data(
                                    data = MainViewState(
                                            characterList = apiResponse.body.results
                                    )
                            )
                        }
                        is ApiErrorResponse -> {
                            value = DataState.error(
                                    message = apiResponse.errorMessage
                            )
                        }
                        is ApiEmptyResponse -> {
                            value = DataState.error(
                                    message = "Empty Response"
                            )
                        }
                    }
                }
            }
        }
    }

    fun getLocations(): LiveData<DataState<MainViewState>>{
        return Transformations.switchMap(
                RetrofitInstance()
                        .buildApi(ApiService::class.java)
                        .getLocations()
        ){ apiResponse ->
            object : LiveData<DataState<MainViewState>>(){
                override fun onActive() {
                    super.onActive()
                    when(apiResponse){
                        is ApiSuccessResponse -> {
                            value = DataState.data(
                                    data = MainViewState(
                                            locationList = apiResponse.body.results
                                    )
                            )
                        }
                        is ApiErrorResponse -> {
                            value = DataState.error(
                                    message = apiResponse.errorMessage
                            )
                        }
                        is ApiEmptyResponse -> {
                            value = DataState.error(
                                    message = "Empty Response"
                            )
                        }
                    }
                }
            }
        }
    }
}