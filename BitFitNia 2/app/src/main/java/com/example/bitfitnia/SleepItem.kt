package com.example.bitfitnia

import kotlinx.serialization.Serializable

@Serializable
class SleepItem(
    val hours : String?,
    val date : String?,
    val notes : String?) : java.io.Serializable {
}