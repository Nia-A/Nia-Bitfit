package com.example.niabitfit

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DashboardFragment : Fragment() {

    var listOfItems = mutableListOf<Nutrition>()
    lateinit var adapter: NutritionAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val intent = Intent(context, LogFragment::class.java)
        val items = intent.getStringExtra(TAG)
        val calories = intent.getStringExtra(TAG2)
        //Log.i("dashboard", items.toString())
        val allItems = Nutrition(items.toString(), calories.toString())
        listOfItems.add(allItems)
        adapter.notifyItemInserted(listOfItems.size - 1)

        val onLongClickListener = object : NutritionAdapter.onLongClickListener {
            override fun onItemLongClicked(position: Int) {
                //Remove the item from the list
                listOfItems.removeAt(position)
                //Notify the adapter that our data set has changed
                adapter.notifyDataSetChanged()
            }
        }

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvList)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = NutritionAdapter(listOfItems, onLongClickListener)
        recyclerView.adapter = adapter

        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            LogFragment().apply {
            }
    }
}