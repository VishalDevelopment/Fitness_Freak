package com.example.bmi.RoomDb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class BmiTable(

    var gender:String ,
    var height:String,
    var weight:String,
    val date:Date,
    val bmiLevel :String,
    @PrimaryKey(autoGenerate = true)
    var id:Int=0

)
