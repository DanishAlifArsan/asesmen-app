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
        fun anakData(classId : String): DatabaseReference {
            return db.reference.child(CLASS_CHILD).child(classId).child(ANAK_CHILD)
        }
        fun penilaianData(classId : String, anakId : String): DatabaseReference {
            return db.reference.child(CLASS_CHILD).child(classId).child(ANAK_CHILD).child(anakId).child(
                PENILAIAN_CHILD)
        }
    }
}