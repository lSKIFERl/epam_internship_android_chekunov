package com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R

/**
 * Holder for types of food in details fragment
 *
 * Provides logic to display for each list item
 * @param itemView view of list item on the screen that was clicked
 */
class FoodTypeHolder (itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    /**
     * Binds this holder with String item
     * @param tag Model bound to this holder
     */
    fun bind(tag: String) {
        itemView.findViewById<TextView>(R.id.small_tag_item).text = tag
    }
}