package com.example.asesmenpaud.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.asesmenpaud.data.ListClass

class ClassViewModel () : ViewModel() {
    val classRepository = ClassRepository()
    fun getAllClass() : LiveData<ListClass> {
        return classRepository.getAllClass()
    }

//    fun uploadStory(image : File, description : String) : LiveData<ErrorResponse> {
//        return storyRepository.uploadStory(image, description)
//    }

    fun progressBar() : LiveData<Boolean> {
        return classRepository.progressBar
    }
}