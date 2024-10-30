package com.example.asesmenpaud.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
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
import com.example.asesmenpaud.data.ListClass
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
                if (anak.gender as Boolean) "Laki-laki"
                        else "Perempuan")
            showPenilaian(anak.id)
        }

        binding.btnPenilaian.setOnClickListener {
            val i = Intent(this, PenilaianCreateActivity::class.java)
            startActivity(i)
        }

        binding.btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun showPenilaian(idAnak : Int) {
        penilaianViewModel.getPenilaian(idAnak).observe(this) {
//            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//            if (it.error == false) {
//                val adapter = StoryAdapter()
//                adapter.submitList(it.listStory.sortedByDescending { it.createdAt} )
//                binding.recyclerView.adapter = adapter
//            }
            var date : List<String> = emptyList()
            var penilaian : Map<String, List<ListPenilaianItem>> = emptyMap()
//            var penilaian = it.listPenilaian.groupBy {
//                p -> p.date
//            }
//            for (i in 0 .. it.listPenilaian.size) {
//
//            } // to do grup berdasarkan tanggalnya

            val adapter = PenilaianAdapter(this, date, penilaian)
            binding.listPenilaian.setAdapter(adapter)
        }
    }

    companion object{
        const val ANAK_KEY = "anak_key"
    }
}