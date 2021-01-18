package com.example.mertkesgin.mvi_example.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mertkesgin.mvi_example.data.model.Character
import com.example.mertkesgin.mvi_example.data.model.Location
import com.example.mertkesgin.mvi_example.repository.Repository
import com.example.mertkesgin.mvi_example.ui.main.state.MainStateEvent
import com.example.mertkesgin.mvi_example.ui.main.state.MainStateEvent.*
import com.example.mertkesgin.mvi_example.ui.main.state.MainViewState
import com.example.mertkesgin.mvi_example.utils.AbsentLiveData
import com.example.mertkesgin.mvi_example.utils.DataState

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    val stateEvent get() = _stateEvent

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    val viewState: LiveData<MainViewState> get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent){ stateEvent ->
            stateEvent?.let {
                handleStateEvent(it)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>>{
        when(stateEvent){
            is GetCharactersEvent -> {
                return Repository().getCharacters()
            }
            is GetLocationsEvent -> {
                return Repository().getLocations()
            }
            is None -> {
                return AbsentLiveData.create()
            }
        }
    }

    fun setCharacterListData(characters: List<Character>){
        val update = getCurrentViewState()
        update.characterList = characters
        _viewState.value = update
    }
    fun setLocationListData(locations: List<Location>){
        val update = getCurrentViewState()
        update.locationList = locations
        _viewState.value = update
    }
    fun getCurrentViewState(): MainViewState {
        return viewState.value ?: MainViewState()
    }
    fun setStateEvent(stateEvent: MainStateEvent){
        _stateEvent.value = stateEvent
    }
}