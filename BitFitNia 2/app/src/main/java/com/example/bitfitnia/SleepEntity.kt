package com.example.bitfitnia

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_table")
data class SleepEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "hours") val hours: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "notes") val notes: String?,
)