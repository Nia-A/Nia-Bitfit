package com.example.niabitfit


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.niabitfit.R


/**
 * A bridge that tells the recycler view how to display the data we give it
 */
class NutritionAdapter(val listOfItems: List<Nutrition>, val longClickListener: onLongClickListener) :
    RecyclerView.Adapter<NutritionAdapter.ViewHolder>() {

    interface  onLongClickListener {
        fun onItemLongClicked(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.food_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get the data model based on position
        val items = listOfItems.get(position)
        holder.item.text = items.item
        holder.calories.text = items.calories
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store references to elements in our layout view
        val item : TextView
        val calories : TextView
        val wordCalories : TextView

        //We also create a constructor that accepts the entire item row
        //and does the view lookups to find each sub-view
        init {
            item = itemView.findViewById(R.id.tvItem)
            calories = itemView.findViewById(R.id.tvPrice)
            wordCalories = itemView.findViewById(R.id.tvWordCalories)

            itemView.setOnLongClickListener {
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }
}