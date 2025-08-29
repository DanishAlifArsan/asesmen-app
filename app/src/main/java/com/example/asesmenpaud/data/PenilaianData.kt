package com.example.asesmenpaud.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PenilaianData(
    val listPenilaian: List<ListPenilaianItem?> = emptyList(),
)

@Parcelize
data class ListPenilaianItem(
    var id : String? = null,
    val desc : String? = null,
    val photoUrl : String? = null,
    val date : String? = null,
    val idAnak : Int? = null,
) : Parcelable