package com.example.mertkesgin.mvi_example.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mertkesgin.mvi_example.R
import com.example.mertkesgin.mvi_example.ui.DataStateListener
import com.example.mertkesgin.mvi_example.utils.DataState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DataStateListener {

    override fun onDataStateChange(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupFragment()
    }

    private fun handleDataStateChange(dataState: DataState<*>?) {
        dataState?.let {
            showProgressBar(dataState.loading)
            dataState.message?.let { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
                .replace(
                        R.id.frameLayout,
                        MainFragment(),
                        "MainFragment"
                ).commit()
    }

    fun showProgressBar(isVisible: Boolean){
        if (isVisible){
            progressBar.visibility = View.VISIBLE
        }else progressBar.visibility = View.GONE
    }
}