package com.example.niabitfit

import android.app.Application

class NutritionApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}