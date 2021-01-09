package com.example.mertkesgin.mvi_example.ui.main.state

sealed class MainStateEvent {

    class GetCharactersEvent: MainStateEvent()
    class GetLocationsEvent: MainStateEvent()
    class None: MainStateEvent()
}