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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mertkesgin.mvi_example.R
import com.example.mertkesgin.mvi_example.adapters.CharacterAdapter
import com.example.mertkesgin.mvi_example.adapters.LocationAdapter
import com.example.mertkesgin.mvi_example.ui.main.state.MainStateEvent
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception

class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var viewModel: MainViewModel
    private val characterAdapter = CharacterAdapter()
    private val locationAdapter = LocationAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerviews()
        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid activity")
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            println("DEBUG: DataState: ${dataState}")

            dataState.data?.let { mainViewState ->
                mainViewState.characterList?.let {
                    viewModel.setCharacterListData(it)
                }

                mainViewState.locationList?.let {
                    viewModel.setLocationListData(it)
                }
            }

            dataState.loading.let {
                Log.d("MainFragment","DEBUG: SHOW LOADING: ${it}")
            }

            dataState.message?.let {
                Log.d("MainFragment","DEBUG: SHOW MESSAGE: ${it}")
            }
        })
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->

            viewState.characterList?.let {
                characterAdapter.differ.submitList(it)
            }
            viewState.locationList?.let {
                locationAdapter.differ.submitList(it)
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

    private fun setupRecyclerviews() {
        rvCharacters.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        }
        rvLocations.apply {
            adapter = locationAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        }
    }
}