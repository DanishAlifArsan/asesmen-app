package com.example.asesmenpaud.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.asesmenpaud.R
import com.example.asesmenpaud.data.ListPenilaianItem
import com.example.asesmenpaud.databinding.ActivityPenilaianCreateBinding
import com.example.asesmenpaud.databinding.ActivityPenilaianDetailBinding

class PenilaianCreateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPenilaianCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityPenilaianCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val penilaian = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(PenilaianDetailActivity.PENILAIAN_KEY, ListPenilaianItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(PenilaianDetailActivity.PENILAIAN_KEY)
        }
        if (penilaian != null) {
            binding.title.text = getString(R.string.edit_penilaian)
            binding.edAddDescription.setText(penilaian.desc)
            Glide.with(this)
                .load(penilaian.photoUrl)
                .into(binding.ivPhoto)
        } else {
            binding.title.text = getString(R.string.tambah_penilaian)
        }

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.btnSave.setOnClickListener {    // buat save ke database
            finish()
        }
    }

    companion object{
        const val PENILAIAN_KEY = "penilaian_key"
    }
}