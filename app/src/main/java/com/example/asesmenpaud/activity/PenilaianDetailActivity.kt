package com.example.asesmenpaud.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.asesmenpaud.R
import com.example.asesmenpaud.data.ListClass
import com.example.asesmenpaud.data.ListPenilaianItem
import com.example.asesmenpaud.databinding.ActivityAnakDetailBinding
import com.example.asesmenpaud.databinding.ActivityPenilaianDetailBinding
import com.example.asesmenpaud.viewmodel.AnakViewModel
import com.example.asesmenpaud.viewmodel.PenilaianViewModel

class PenilaianDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPenilaianDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityPenilaianDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val penilaian = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(PENILAIAN_KEY, ListPenilaianItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(PENILAIAN_KEY)
        }
        if (penilaian != null) {
            binding.penilaianDesc.text = penilaian.desc
            Glide.with(this)
                .load(penilaian.photoUrl)
                .into(binding.photo)
        }
    }

    companion object{
        const val PENILAIAN_KEY = "penilaian_key"
    }
}