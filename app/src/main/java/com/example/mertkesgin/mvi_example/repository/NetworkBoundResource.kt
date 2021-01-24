package com.example.mertkesgin.mvi_example.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.mertkesgin.mvi_example.utils.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResponseObject,ViewStateType> {

    protected val result = MediatorLiveData<DataState<ViewStateType>>()

    init {
        result.value = DataState.loading(true)
        GlobalScope.launch(IO) {
            withContext(Main){
                val apiResponse = createApiCall()

                result.addSource(apiResponse){ response ->
                    result.removeSource(apiResponse)

                    handleNetworkCall(response)
                }
            }
        }
    }

    fun handleNetworkCall(response: ApiResponse<ResponseObject>){
        when(response){
            is ApiSuccessResponse -> {
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse -> {
                Log.d("NetworkBoundResource:", "${response.errorMessage}")
                onReturnError(response.errorMessage)
            }
            is ApiEmptyResponse -> {
                Log.d("NetworkBoundResource:", "Returned Nothing")
                onReturnError("Returned Nothing")
            }
        }
    }

    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    fun onReturnError(message: String){
        result.value = DataState.error(message)
    }

    abstract fun createApiCall(): LiveData<ApiResponse<ResponseObject>>

    fun  asLiveData() = result as LiveData<DataState<ViewStateType>>
}