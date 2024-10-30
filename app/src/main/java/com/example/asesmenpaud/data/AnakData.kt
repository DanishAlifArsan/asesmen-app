package com.example.asesmenpaud.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AnakData(
    val listAnak: List<ListAnakItem> = emptyList(),
)

@Parcelize
data class ListAnakItem(
    val id : Int,
    val name : String? = null,
    val age : Int? = null,
    val gender : Boolean? = null,
    val weight : Int? = null,
    val height : Int? = null,
    val listPenilaian : List<ListPenilaianItem> = emptyList(),
) : Parcelable

@Parcelize
data class ListPenilaianItem(
    val id : Int,
    val desc : String? = null,
    val photoUrl : String? = null
) : Parcelable