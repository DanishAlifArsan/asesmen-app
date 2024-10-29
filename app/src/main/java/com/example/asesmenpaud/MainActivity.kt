package com.example.asesmenpaud

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asesmenpaud.data.ListClass
import com.example.asesmenpaud.databinding.ActivityMainBinding
import com.example.asesmenpaud.viewmodel.ClassViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val classViewModel: ClassViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showStoryList()
    }

    private fun showStoryList() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

//        val storyViewModel = obtainViewModel(this@MainActivity)
        classViewModel.getAllClass().observe(this) {
//            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//            if (it.error == false) {
//                val adapter = StoryAdapter()
//                adapter.submitList(it.listStory.sortedByDescending { it.createdAt} )
//                binding.recyclerView.adapter = adapter
//            }
            val adapter = ClassAdapter()
            adapter.submitList(listOf(it))
            binding.recyclerView.adapter = adapter
        }

        classViewModel.progressBar().observe(this) {
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
}