package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.asesmenpaud.data.AnakData

class AnakViewModel : ViewModel() {
    val anakRepository = AnakRepository()
    fun getAllAnak() : LiveData<AnakData> {
        return anakRepository.getAllAnak()
    }

//    fun uploadStory(image : File, description : String) : LiveData<ErrorResponse> {
//        return storyRepository.uploadStory(image, description)
//    }

    fun progressBar() : LiveData<Boolean> {
        return anakRepository.progressBar
    }
}