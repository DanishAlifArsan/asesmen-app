package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asesmenpaud.data.ClassData
import com.example.asesmenpaud.data.Database
import com.example.asesmenpaud.data.ListClassItem
import com.example.asesmenpaud.utils.Event

class ClassRepository {
    val progressBar = MutableLiveData<Boolean>()
    private val _snackbarText = MutableLiveData<Event<String>>()

    fun getAllClass() : MutableLiveData<ClassData> {
        val classResponse = MutableLiveData<ClassData>()
        progressBar.value = true

        Database.classData().get().addOnSuccessListener {
            val classList = mutableListOf<ListClassItem?>()
            for (messageSnapshot in it.children) {
                val listClassItem = messageSnapshot.getValue(ListClassItem::class.java)
                classList.add(listClassItem)
            }

            classResponse.value = ClassData(classList.toList())
            progressBar.value = false
        }.addOnFailureListener{
            _snackbarText.value = Event("Gagal mendapatkan data kelas")
            progressBar.value = false
        }

        return classResponse
    }

    val snackbarText: LiveData<Event<String>> = _snackbarText
}