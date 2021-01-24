package com.example.mertkesgin.mvi_example.ui

import com.example.mertkesgin.mvi_example.utils.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}