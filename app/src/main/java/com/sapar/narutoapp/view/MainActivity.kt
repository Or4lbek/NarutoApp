package com.sapar.narutoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sapar.narutoapp.R
import com.sapar.narutoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
    }

}