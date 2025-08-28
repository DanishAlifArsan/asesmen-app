package com.example.asesmenpaud.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asesmenpaud.data.ClassData
import com.example.asesmenpaud.utils.Event

class ClassViewModel () : ViewModel() {
    val classRepository = ClassRepository()
    fun getAllClass() : LiveData<ClassData> {
        return classRepository.getAllClass()
    }

//    fun uploadStory(image : File, description : String) : LiveData<ErrorResponse> {
//        return storyRepository.uploadStory(image, description)
//    }

    fun progressBar() : LiveData<Boolean> {
        return classRepository.progressBar
    }

    fun snackbarText() : LiveData<Event<String>> {
        return classRepository.snackbarText
    }
}