package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asesmenpaud.data.AnakData
import com.example.asesmenpaud.data.ListPenilaianItem
import com.example.asesmenpaud.data.PenilaianData
import com.example.asesmenpaud.utils.Event
import com.google.firebase.database.DatabaseError

class PenilaianViewModel : ViewModel() {
    val penilaianRepository = PenilaianRepository()
    fun getPenilaian(idAnak : Int) : LiveData<PenilaianData> {
        return penilaianRepository.getPenilaian(idAnak)
    }

//    fun uploadStory(image : File, description : String) : LiveData<ErrorResponse> {
//        return storyRepository.uploadStory(image, description)
//    }

    fun createPenilaian(listPenilaianItem : ListPenilaianItem) : LiveData<DatabaseError?> {
        return penilaianRepository.createPenilaian(listPenilaianItem)
    }

    fun progressBar() : LiveData<Boolean> {
        return penilaianRepository.progressBar
    }

    fun snackbarText() : LiveData<Event<String>> {
        return penilaianRepository.snackbarText
    }
}