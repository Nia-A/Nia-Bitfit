package com.example.bitfitnia

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val SLEEP_EXTRA = "SLEEP_EXTRA"

lateinit var application: Application

class SleepAdapter(private val sleepLog : ArrayList<SleepItem>, private val activity: MainActivity)
    : RecyclerView.Adapter<SleepAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val hoursTextView = itemView.findViewById<TextView>(R.id.hours)
        private val dateTextView = itemView.findViewById<TextView>(R.id.date)
        private val notesTextView = itemView.findViewById<TextView>(R.id.notes)
        private val removeButton = itemView.findViewById<Button>(R.id.removeBtn)
        init {
            removeButton.setOnClickListener {
                removeAt(adapterPosition)
            }
        }
        fun bind(sleepItem: SleepItem) {
            hoursTextView.text = sleepItem.hours
            dateTextView.text = sleepItem.date
            notesTextView.text = sleepItem.notes
        }
    }
//    , View.OnClickListener {
//        private val hoursTextView = itemView.findViewById<TextView>(R.id.hours)
//        private val dateTextView = itemView.findViewById<TextView>(R.id.date)
//        private val notesTextView = itemView.findViewById<TextView>(R.id.notes)
//        init {
//            itemView.setOnClickListener(this)
//        }
//    }

    fun removeAt(position: Int) {
        val sleepItem = sleepLog[position]
        sleepLog.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, sleepLog.size)
        activity.delete(sleepItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sleep_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val log = sleepLog[position]
        holder.bind(log)
    }

    override fun getItemCount() = sleepLog.size
}