package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asesmenpaud.data.AnakData
import com.example.asesmenpaud.data.Database
import com.example.asesmenpaud.data.ListPenilaianItem
import com.example.asesmenpaud.data.PenilaianData
import com.example.asesmenpaud.utils.Event
import com.google.firebase.database.DatabaseError

class PenilaianRepository {
    val progressBar = MutableLiveData<Boolean>()
    private val _snackbarText = MutableLiveData<Event<String>>()

    fun getPenilaian(idAnak : Int) : MutableLiveData<PenilaianData> {
        val penilaianResponse = MutableLiveData<PenilaianData>()
        progressBar.value = true

        Database.penilaianData().get().addOnSuccessListener { response ->
            val penilaianList = mutableListOf<ListPenilaianItem>()
            for (messageSnapshot in response.children) {
                val listClassItem = messageSnapshot.getValue(ListPenilaianItem::class.java)
                if (listClassItem != null) {
                    penilaianList.add(listClassItem)
                }
            }

            val filteredPenilaian = PenilaianData(penilaianList.filter {it.idAnak == idAnak})
            penilaianResponse.value = filteredPenilaian
            progressBar.value = false
        }.addOnFailureListener{
            _snackbarText.value = Event("Gagal mendapatkan data penilaian")
            progressBar.value = false
        }

        return penilaianResponse
    }

    fun createPenilaian(listPenilaianItem: ListPenilaianItem) : MutableLiveData<DatabaseError?> {
        progressBar.value = true
        val error = MutableLiveData<DatabaseError?>()
        val penilaianRef = Database.penilaianData().push()
        listPenilaianItem.id = penilaianRef.key

        penilaianRef.setValue(listPenilaianItem) { err, _  ->
            error.value = err
            if (err != null) {
                _snackbarText.value = Event("Sukses menambahkan data penilaian")
                progressBar.value = false
            } else {
                _snackbarText.value = Event("Gagal menambahkan data penilaian")
                progressBar.value = false
            }
        }

        return error
    }

    val snackbarText: LiveData<Event<String>> = _snackbarText
}