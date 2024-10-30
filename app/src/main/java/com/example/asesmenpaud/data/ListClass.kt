package com.example.asesmenpaud.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListClass(
    val classId : Int,
    val className : String? = null,
    val classDesc : String? = null,
    val semester : String? = null,
    val year : String? = null,
) : Parcelable