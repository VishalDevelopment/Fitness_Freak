package com.example.bmi.RoomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BmiDAO {
    @Insert
    suspend  fun insertData(bmiTable: BmiTable)
    @Update
    suspend  fun updateData(bmiTable: BmiTable)
    @Delete
suspend    fun deleteData(bmiTable: BmiTable)

    @Query("Select * from bmiData")
 suspend   fun getAllData():List<BmiTable>

}