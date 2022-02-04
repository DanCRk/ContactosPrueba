package com.prueba.contactos.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prueba.contactos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Settear la vista en el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}