package com.example.niabitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NutritionDao {
    @Query("SELECT * FROM calorie_table")
    fun getAll(): Flow<List<NutritionEntity>>

    @Insert
    fun insertAll(entries: List<NutritionEntity>)

    @Query("DELETE FROM calorie_table")
    fun deleteAll()
}