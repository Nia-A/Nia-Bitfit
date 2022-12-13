package com.example.bitfitnia

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDao {
    @Query("SELECT * FROM sleep_table")
    fun getAll(): Flow<List<SleepEntity>>

    @Insert
    fun insert(sleepEntity: SleepEntity)

    @Query("DELETE FROM sleep_table")
    fun deleteAll()

    @Delete
    fun delete(sleepEntity: SleepEntity)
}