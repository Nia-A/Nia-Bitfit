package com.example.niabitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calorie_table")
data class NutritionEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "foodItem") val food: String?,
    @ColumnInfo(name = "calories") val calories: String?,
)