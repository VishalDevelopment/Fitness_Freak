package com.example.bmi.RoomDb

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BmiTable::class],
    version = 1
)
abstract class BmiDatabase :RoomDatabase(){
    abstract fun BmiDao():BmiDAO
    
    companion object{
        private var INSTANCE :BmiDatabase?= null
        
        fun getDao(context: Context): BmiDatabase {
            if (INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context,BmiDatabase::class.java,"Bmi_db").build()
            }
            return INSTANCE!!
        }
    }
}