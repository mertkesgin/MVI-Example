package com.example.mertkesgin.mvi_example.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mertkesgin.mvi_example.ui.main.state.MainStateEvent
import com.example.mertkesgin.mvi_example.ui.main.state.MainStateEvent.*
import com.example.mertkesgin.mvi_example.ui.main.state.MainViewState
import com.example.mertkesgin.mvi_example.utils.AbsentLiveData

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    val stateEvent get() = _stateEvent

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    val viewState: LiveData<MainViewState> get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent){ stateEvent ->
            stateEvent?.let {
                handleStateEvent(it)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState>{
        when(stateEvent){
            is GetCharactersEvent -> {
                return AbsentLiveData.create()
            }
            is GetLocationsEvent -> {
                return AbsentLiveData.create()
            }
            is None -> {
                return AbsentLiveData.create()
            }
        }
    }
}