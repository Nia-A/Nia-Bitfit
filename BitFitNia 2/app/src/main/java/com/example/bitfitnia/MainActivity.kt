package com.example.bitfitnia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var sleepLog : ArrayList<SleepItem>
    lateinit var sleepAdapter: SleepAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sleepRv = findViewById<RecyclerView>(R.id.sleepLogs)
        sleepLog = ArrayList()
        sleepAdapter = SleepAdapter(sleepLog, this@MainActivity)
        sleepRv.adapter = sleepAdapter
        lifecycleScope.launch {
            (application as BitfitApplication).db.sleepDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    SleepItem(
                        entity.hours,
                        entity.date,
                        entity.notes
                    )
                }.also { mappedList ->
                    sleepLog.clear()
                    sleepLog.addAll(mappedList)
                    sleepAdapter.notifyDataSetChanged()
                }
            }
        }
        sleepRv.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            sleepRv.addItemDecoration(dividerItemDecoration)
        }

        findViewById<Button>(R.id.deleteAll).setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as BitfitApplication).db.sleepDao().deleteAll()
            }
        }


//        var sleepItem : SleepItem
//
//        var sleepActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result ->
//            // If the user comes back to this activity from EditActivity
//            // with no error or cancellation
//            if (result.resultCode == Activity.RESULT_OK) {
//                val data = result.data
//                // Get the data passed from EditActivity
//                if (data != null) {
//                    sleepItem = data.extras!!.get(SLEEP_EXTRA) as SleepItem
//                    //sleepLog.add(sleepItem)
//                    //sleepAdapter.notifyItemInserted(sleepLog.size - 1)
//                    lifecycleScope.launch(IO) {
//                        (application as BitfitApplication).db.sleepDao().insert(sleepItem)
//                    }
//
//                }
//            }
//        }
        findViewById<Button>(R.id.record).setOnClickListener {
            val intent = Intent(this, SleepActivity::class.java)
            startActivity(intent)
            //sleepActivityResultLauncher.launch(intent)
        }
    }

    fun delete(sleepItem : SleepItem) {
        lifecycleScope.launch(IO) {
//            (application as BitfitApplication).db.sleepDao().getAll().collect { databaseList ->
//                databaseList[position].d
//            }
            (application as BitfitApplication).db.sleepDao().delete(
                SleepEntity(
                    hours = sleepItem.hours,
                    date = sleepItem.date,
                    notes = sleepItem.notes
                )
            )
        }
    }

}