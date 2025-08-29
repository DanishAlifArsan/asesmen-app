package com.example.asesmenpaud.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asesmenpaud.R
import com.example.asesmenpaud.activity.adapter.AnakAdapter
import com.example.asesmenpaud.activity.adapter.PenilaianAdapter
import com.example.asesmenpaud.data.ListAnakItem
import com.example.asesmenpaud.data.ListPenilaianItem
import com.example.asesmenpaud.databinding.ActivityAnakDetailBinding
import com.example.asesmenpaud.databinding.ActivityClassDetailBinding
import com.example.asesmenpaud.viewmodel.AnakViewModel
import com.example.asesmenpaud.viewmodel.PenilaianViewModel

class AnakDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAnakDetailBinding
    private val penilaianViewModel: PenilaianViewModel by viewModels()

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
                if (anak.   gender as Boolean) "Laki-laki"
                        else "Perempuan")
            showPenilaian(anak.id as Int)

            binding.btnPenilaian.setOnClickListener {
                val i = Intent(this, PenilaianCreateActivity::class.java)
                i.putExtra(PenilaianCreateActivity.NEW_PENILAIAN_KEY, anak.id);
                startActivity(i)
            }
        }

        binding.btnBack.setOnClickListener{
            finish()
        }
    }

    private fun showPenilaian(idAnak : Int) {
        penilaianViewModel.getPenilaian(idAnak).observe(this) {
            val listPenilaian = it.listPenilaian as List<ListPenilaianItem>
            val penilaian : Map<String, List<ListPenilaianItem>> = listPenilaian.groupBy {
                p -> p.date.toString()
            }
            val date : List<String>  = penilaian.keys.toList()
            val adapter = PenilaianAdapter(this, date, penilaian)
            binding.listPenilaian.setAdapter(adapter)
        }

        penilaianViewModel.snackbarText().observe(this) {
            it.getContentIfNotHandled()?.let { snackBarText ->
                Toast.makeText(
                    this,
                    snackBarText,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        penilaianViewModel.progressBar().observe(this) {
            showProgressBar(it)
        }
    }

    private fun showProgressBar(status : Boolean) {
        binding.progressBar.visibility = if (status) View.VISIBLE else View.GONE
    }

    companion object{
        const val ANAK_KEY = "anak_key"
    }
}