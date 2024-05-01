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
   suspend fun insertData(bmiTable: BmiTable)

    @Query("Select * from bmitable")
  fun getAllData():List<BmiTable>

}