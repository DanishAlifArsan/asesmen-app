package com.example.asesmenpaud.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.asesmenpaud.data.ListClass
import kotlinx.coroutines.launch
import java.io.File

class ClassRepository {
    val progressBar = MutableLiveData<Boolean>()

    fun getAllClass() : MutableLiveData<ListClass> {
        val classResponse = MutableLiveData<ListClass>()
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

        val listClass = ListClass(1, "A", "Lorem ipsum", "I", "2024-2025")
        classResponse.value = listClass
        progressBar.value = false

        return classResponse
    }
}