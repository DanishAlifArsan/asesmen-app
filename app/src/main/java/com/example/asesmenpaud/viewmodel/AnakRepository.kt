package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.asesmenpaud.data.AnakData
import com.example.asesmenpaud.data.ListAnakItem

class AnakRepository {
    val progressBar = MutableLiveData<Boolean>()

    fun getAllAnak() : MutableLiveData<AnakData> {
        val anakResponse = MutableLiveData<AnakData>()
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

        val listAnak = AnakData( listOf(
            ListAnakItem(1, "Adi", 1, true, 20, 100),
            ListAnakItem(2, "Budi", 2, true, 20, 100),
            ListAnakItem(3, "Caca", 1, false, 20, 100),
        ))
        anakResponse.value = listAnak
        progressBar.value = false

        return anakResponse
    }
}