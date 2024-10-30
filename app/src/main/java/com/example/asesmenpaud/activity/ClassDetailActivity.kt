package com.example.asesmenpaud.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asesmenpaud.activity.adapter.AnakAdapter
import com.example.asesmenpaud.R
import com.example.asesmenpaud.data.ListClass
import com.example.asesmenpaud.databinding.ActivityClassDetailBinding
import com.example.asesmenpaud.viewmodel.AnakViewModel

class ClassDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityClassDetailBinding
    private val anakViewModel: AnakViewModel by viewModels()
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
            binding.semester.text = getString(R.string.semester, myClass.semester)
            binding.tahunAjaran.text = getString(R.string.tahun_ajaran, myClass.year)
        }

        showAnak()

        binding.btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun showAnak() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

//        val storyViewModel = obtainViewModel(this@MainActivity)
        anakViewModel.getAllAnak().observe(this) {
//            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//            if (it.error == false) {
//                val adapter = StoryAdapter()
//                adapter.submitList(it.listStory.sortedByDescending { it.createdAt} )
//                binding.recyclerView.adapter = adapter
//            }
            val adapter = AnakAdapter()
            adapter.submitList(it.listAnak)
            binding.recyclerView.adapter = adapter
        }

        anakViewModel.progressBar().observe(this) {
            showProgressBar(it)
        }
    }

//    private fun obtainViewModel(activity: AppCompatActivity) : StoryViewModel {
//        val factory = StoryViewModelFactory.getInstance(activity)
//        return ViewModelProvider(activity, factory).get(StoryViewModel::class.java)
//    }

    private fun showProgressBar(status : Boolean) {
        binding.progressBar.visibility = if (status) View.VISIBLE else View.GONE
    }

    companion object{
        const val CLASS_KEY = "class_key"
    }
}