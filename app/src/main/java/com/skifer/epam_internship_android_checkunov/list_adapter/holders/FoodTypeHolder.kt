package com.skifer.epam_internship_android_checkunov.list_adapter.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.food_types.FoodType

/**
 * Holder for types of food in details fragment
 *
 * Provides logic to display for each list item
 * @param itemView view of list item on the screen that was clicked
 */
class FoodTypeHolder (itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    /**Held model*/
    lateinit var foodType: FoodType

    /**
     * Binds this holder with [FoodType] item
     * @param foodType Model bound to this holder
     */
    fun bind(foodType: FoodType) {
        this.foodType = foodType
        itemView.findViewById<TextView>(R.id.small_type_item).text = foodType.name
    }
}