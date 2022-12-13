package com.example.bitfitnia

import android.app.Application

class BitfitApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}