package com.example.asesmenpaud.data

import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
class Database {
    companion object {
        private var db: FirebaseDatabase = Firebase.database
        const val CLASS_CHILD = "classes"
        const val ANAK_CHILD = "anak"
        const val PENILAIAN_CHILD = "penilaian"
        fun classData(): DatabaseReference {
            return db.reference.child(CLASS_CHILD)
        }
        fun anakData(): DatabaseReference {
            return db.reference.child(ANAK_CHILD)
        }
        fun penilaianData(): DatabaseReference {
            return db.reference.child(PENILAIAN_CHILD)
        }
    }
}