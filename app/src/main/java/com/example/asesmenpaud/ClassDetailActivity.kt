package com.example.asesmenpaud

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.asesmenpaud.data.ListClass
import com.example.asesmenpaud.databinding.ActivityClassDetailBinding
import com.example.asesmenpaud.databinding.ActivityMainBinding

class ClassDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityClassDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityClassDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myClass = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(CLASS_KEY, ListClass::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(CLASS_KEY)
        }
        if (myClass != null) {
            binding.className.text = getString(R.string.nama_kelas, myClass.className)
            binding.classDesc.text = "${myClass.classDesc}"
        }
    }

    companion object{
        const val CLASS_KEY = "class_key"
    }
}