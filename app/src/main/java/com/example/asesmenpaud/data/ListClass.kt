package com.example.asesmenpaud.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ClassData(
    val listClass: List<ListClassItem?> = emptyList(),
)
@Parcelize
data class ListClassItem(
    val classId : Int? = 0,
    val className : String? = null,
    val classDesc : String? = null,
    val semester : String? = null,
    val year : String? = null,
) : Parcelable