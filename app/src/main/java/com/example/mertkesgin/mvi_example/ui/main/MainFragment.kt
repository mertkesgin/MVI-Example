package com.example.mertkesgin.mvi_example.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mertkesgin.mvi_example.R
import com.example.mertkesgin.mvi_example.ui.main.state.MainStateEvent
import java.lang.Exception

class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid activity")
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            println("DEBUG: DataState: ${dataState}")
            dataState.characterList?.let { characterList ->
                viewModel.setCharacterListData(characterList)
            }
            dataState.locationList?.let { locationList ->
                viewModel.setLocationListData(locationList)
            }
        })
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->

            viewState.characterList?.let {
                Log.d("MainFragment","DataState: init views ${it}")
            }
            viewState.locationList?.let {
                Log.d("MainFragment","DataState: init views ${it}")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_get_characters -> triggerGetCharactersEvent()
            R.id.action_get_locations -> triggerGetLocationsEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetCharactersEvent() {
        viewModel.setStateEvent(MainStateEvent.GetCharactersEvent())
    }

    private fun triggerGetLocationsEvent() {
        viewModel.setStateEvent(MainStateEvent.GetLocationsEvent())
    }
}