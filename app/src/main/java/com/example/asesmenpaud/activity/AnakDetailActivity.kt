package com.example.asesmenpaud.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.asesmenpaud.R
import com.example.asesmenpaud.data.ListAnakItem
import com.example.asesmenpaud.data.ListClass
import com.example.asesmenpaud.databinding.ActivityAnakDetailBinding
import com.example.asesmenpaud.databinding.ActivityClassDetailBinding

class AnakDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAnakDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityAnakDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anak = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(ANAK_KEY, ListAnakItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(ANAK_KEY)
        }
        if (anak != null) {
            binding.anakName.text = anak.name
            binding.tinggiValue.text = getString(R.string.tinggi_value, anak.height)
            binding.beratValue.text = getString(R.string.berat_value, anak.weight)

            binding.usiaValue.text = getString(R.string.usia_value,
                when(anak.age) {
                    1 -> "3-4"
                    2 -> "4-5"
                    else -> "5-6"
                })
            binding.kelaminValue.text = getString(R.string.kelamin_value,
                if (anak.gender as Boolean) "Laki-laki"
                        else "Perempuan")
        }
    }

    companion object{
        const val ANAK_KEY = "anak_key"
    }
}