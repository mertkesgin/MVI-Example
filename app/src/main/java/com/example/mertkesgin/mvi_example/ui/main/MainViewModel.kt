package com.example.mertkesgin.mvi_example.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mertkesgin.mvi_example.data.model.Character
import com.example.mertkesgin.mvi_example.data.model.Location
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
                return object : LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        val characterList: ArrayList<Character> = ArrayList()
                        characterList.add(
                                Character(1,"CharacterName","image","created","gender","species","status","type","url")
                        )
                        characterList.add(
                                Character(1,"CharacterName2","image2","created2","gender2","species2","status2","type2","url2")
                        )
                        value = MainViewState(characterList)
                    }
                }
            }
            is GetLocationsEvent -> {
                return object : LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        val locationList: ArrayList<Location> = ArrayList()
                        locationList.add(
                                Location(1,"name","type","dimension","url","created")
                        )
                        locationList.add(
                                Location(2,"name2","type2","dimension2","url2","created2")
                        )
                        value = MainViewState(locationList = locationList)
                    }
                }
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