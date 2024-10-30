package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.asesmenpaud.data.AnakData
import com.example.asesmenpaud.data.PenilaianData

class PenilaianViewModel : ViewModel() {
    val penilaianRepository = PenilaianRepository()
    fun getPenilaian(idAnak : Int) : LiveData<PenilaianData> {
        return penilaianRepository.getPenilaian(idAnak)
    }

//    fun uploadStory(image : File, description : String) : LiveData<ErrorResponse> {
//        return storyRepository.uploadStory(image, description)
//    }

    fun progressBar() : LiveData<Boolean> {
        return penilaianRepository.progressBar
    }
}