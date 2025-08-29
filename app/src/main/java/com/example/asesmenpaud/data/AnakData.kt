package com.example.asesmenpaud.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AnakData(
    val listAnak: List<ListAnakItem?> = emptyList(),
)

@Parcelize
data class ListAnakItem(
    val id : Int? = 0,
    val name : String? = null,
    val age : Int? = null,
    val gender : Boolean? = null,
    val weight : Int? = null,
    val height : Int? = null,
    val listPenilaian : List<ListPenilaianItem> = emptyList(),
    val classId : Int? = 0,
) : Parcelable