package com.example.bmi.RoomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bmiData")
data class BmiTable(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0,
    var gender:String,
    var height:String,
    var weight:String
)
