package com.example.asesmenpaud

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.asesmenpaud.databinding.ActivityClassDetailBinding
import com.example.asesmenpaud.databinding.ActivityMainBinding

class ClassDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityClassDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityClassDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}