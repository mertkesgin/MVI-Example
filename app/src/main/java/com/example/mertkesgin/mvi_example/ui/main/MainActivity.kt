package com.example.mertkesgin.mvi_example.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mertkesgin.mvi_example.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
                .replace(
                        R.id.frameLayout,
                        MainFragment(),
                        "MainFragment"
                ).commit()
    }
}