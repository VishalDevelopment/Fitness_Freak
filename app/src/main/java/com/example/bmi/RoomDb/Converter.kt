package com.example.bmi.RoomDb

import androidx.room.TypeConverter
import java.util.Date

class Converter {

    @TypeConverter
    fun fromDateToLong(value: Date):Long{
        return value.time
    }
@TypeConverter
    fun fromLongtoDate(value:Long):Date{
        return Date(value)
    }
}