package com.example.niabitfit

import android.content.Context
import android.content.Intent
import android.database.DatabaseErrorHandler
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


const val TAG = "ITEM"
const val TAG2 = "CALORIES"
class LogFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_log, container, false)

        //values to reference the views from the layout file
        val foodItem = view.findViewById<EditText>(R.id.etFoodItem)
        val etCalories = view.findViewById<EditText>(R.id.etCalories)
        val submitBtn = view.findViewById<Button>(R.id.submitBtn)

        submitBtn.setOnClickListener {
            //Grab the text that the user inputs
            val item = foodItem.text.toString()
            val calories = etCalories.text.toString()

            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(TAG, item)
            intent.putExtra(TAG2, calories)

            //Reset text field after user adds item to the wishlist
            foodItem.setText("")
            etCalories.setText("")
            foodItem.hideKeyboard()
        }
        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            LogFragment().apply {
            }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}