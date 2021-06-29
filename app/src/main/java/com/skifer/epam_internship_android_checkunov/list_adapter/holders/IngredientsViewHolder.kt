package com.skifer.epam_internship_android_checkunov.list_adapter.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.model.Ingredient

/**
 * Holder for ingredients of dish in details fragment
 *
 * Provides logic to display for each list item
 * @param itemView view of list item on the screen that was clicked
 */
class IngredientsViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    /**Held item*/
    lateinit var ingredient: Ingredient

    /**
     * Binds this holder with [Ingredient] item
     * @param ingredient Model bound to this holder
     */
    fun bind(ingredient: Ingredient) {
        this.ingredient = ingredient
        itemView.findViewById<TextView>(R.id.ingredient_item).text = StringBuilder("${ingredient.name} - ${ingredient.count} ${ingredient.measure}")
    }
}