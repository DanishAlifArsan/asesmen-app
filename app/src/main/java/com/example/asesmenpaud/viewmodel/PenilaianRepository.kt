package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.asesmenpaud.data.AnakData
import com.example.asesmenpaud.data.ListAnakItem
import com.example.asesmenpaud.data.ListPenilaianItem
import com.example.asesmenpaud.data.PenilaianData

class PenilaianRepository {
    val progressBar = MutableLiveData<Boolean>()

    fun getPenilaian(idAnak : Int) : MutableLiveData<PenilaianData> {
        val penilaianResponse = MutableLiveData<PenilaianData>()
        progressBar.value = true
//        scope.launch{
//            try {
//                storyResponse.postValue(apiService.getAllStories())
//                progressBar.postValue(false)
//            } catch (e : HttpException) {
//                val jsonInString = e.response()?.errorBody()?.string()
//                val errorBody = Gson().fromJson(jsonInString, StoryResponse::class.java)
//                storyResponse.postValue(errorBody)
//                progressBar.postValue(false)
//            }
//        }

        val listPenilaian = PenilaianData( listOf(
            ListPenilaianItem(1,"Ananda mengerti satu", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnf_Fyd39gBMLjI_PG6MwsGGo1qjzbmG0X5g&s", "30/10/2024",1),
            ListPenilaianItem(2,"Ananda mengerti dua", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnf_Fyd39gBMLjI_PG6MwsGGo1qjzbmG0X5g&s", "1/10/2024",2),
            ListPenilaianItem(3,"Ananda mengerti tiga", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnf_Fyd39gBMLjI_PG6MwsGGo1qjzbmG0X5g&s", "20/10/2024",3),
        ))

        val filteredPenilaian = PenilaianData(listPenilaian.listPenilaian.filter {it.idAnak == idAnak})

        penilaianResponse.value = filteredPenilaian

        progressBar.value = false

        return penilaianResponse
    }
}