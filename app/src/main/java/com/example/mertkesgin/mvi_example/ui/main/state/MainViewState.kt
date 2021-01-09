package com.example.mertkesgin.mvi_example.ui.main.state

import com.example.mertkesgin.mvi_example.data.model.Character
import com.example.mertkesgin.mvi_example.data.model.Location

data class MainViewState(
    var characterList: List<Character>? = null,
    var locationList: List<Location>? = null
)