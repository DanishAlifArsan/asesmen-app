package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asesmenpaud.data.AnakData
import com.example.asesmenpaud.data.ClassData
import com.example.asesmenpaud.data.Database
import com.example.asesmenpaud.data.ListAnakItem
import com.example.asesmenpaud.data.ListClassItem
import com.example.asesmenpaud.data.PenilaianData
import com.example.asesmenpaud.utils.Event

class AnakRepository {
    val progressBar = MutableLiveData<Boolean>()
    private val _snackbarText = MutableLiveData<Event<String>>()

    fun getAllAnak(classId : Int) : MutableLiveData<AnakData> {
        val anakResponse = MutableLiveData<AnakData>()
        progressBar.value = true

        Database.anakData().get().addOnSuccessListener { response ->
            val anakList = mutableListOf<ListAnakItem>()
            for (messageSnapshot in response.children) {
                val listAnakItem = messageSnapshot.getValue(ListAnakItem::class.java)
                if (listAnakItem != null) {
                    anakList.add(listAnakItem)
                }
            }

            val filteredAnak = AnakData(anakList.filter {it.classId == classId})
            anakResponse.value = filteredAnak
            progressBar.value = false
        }.addOnFailureListener{
            _snackbarText.value = Event("Gagal mendapatkan data anak")
            progressBar.value = false
        }

        return anakResponse
    }

    val snackbarText: LiveData<Event<String>> = _snackbarText
}