package com.example.bitfitnia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SleepActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sleep_activity)

        findViewById<Button>(R.id.record2).setOnClickListener {
            val hours = findViewById<EditText>(R.id.hours_slept)
            val date = findViewById<EditText>(R.id.sleep_date)
            val notes = findViewById<EditText>(R.id.sleep_notes)

            val sleepItem = SleepItem(hours.text.toString(), date.text.toString(), notes.text.toString())

            hours.setText("")
            date.setText("")
            notes.setText("")

            lifecycleScope.launch(Dispatchers.IO) {
                (application as BitfitApplication).db.sleepDao().insert(
                    SleepEntity(
                        hours = sleepItem.hours,
                        date = sleepItem.date,
                        notes = sleepItem.notes
                    )
                )
            }
//            val data = Intent()
//            data.putExtra(SLEEP_EXTRA, sleepItem)
//            setResult(RESULT_OK, data)
            finish()
        }
    }
}