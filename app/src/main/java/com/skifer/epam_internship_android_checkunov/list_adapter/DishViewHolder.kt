package com.skifer.epam_internship_android_checkunov.list_adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.model.MealModel

/**
 * Holder for dish list
 * @param itemView view of list item on the screen that was clicked
 * @param onItemListener Click listener for each list item
 */
class DishViewHolder(itemView: View, onItemListener: Adapter.onItemListener)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    /**label with name of the dish for list*/
    var dishLabel: TextView? = null

    /**simple icon of the dish for list*/
    var dishImage: ImageView? = null

    /**model of containment dish*/
    lateinit var meal: MealModel

    /**click listener*/
    val onItemListener: Adapter.onItemListener

    init {
        dishLabel = itemView.findViewById(R.id.dishLabel)
        dishImage = itemView.findViewById(R.id.dishImage)
        this.onItemListener = onItemListener

        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        this.onItemListener.onItemClick(meal)
    }
}