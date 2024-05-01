package com.example.bmi.RoomDb

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(
    entities = [BmiTable::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class BmiDatabase : RoomDatabase() {
    abstract fun BmiDao(): BmiDAO

    companion object {
        private var INSTANCE: BmiDatabase? = null

        fun getDatabase(context: Context): BmiDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, BmiDatabase::class.java, "Bmi_db")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}