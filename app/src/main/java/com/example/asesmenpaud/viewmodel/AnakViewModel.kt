package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.asesmenpaud.data.AnakData
import com.example.asesmenpaud.utils.Event

class AnakViewModel : ViewModel() {
    val anakRepository = AnakRepository()
    fun getAllAnak(classId : Int) : LiveData<AnakData> {
        return anakRepository.getAllAnak(classId)
    }

//    fun uploadStory(image : File, description : String) : LiveData<ErrorResponse> {
//        return storyRepository.uploadStory(image, description)
//    }

    fun progressBar() : LiveData<Boolean> {
        return anakRepository.progressBar
    }

    fun snackbarText() : LiveData<Event<String>> {
        return anakRepository.snackbarText
    }
}