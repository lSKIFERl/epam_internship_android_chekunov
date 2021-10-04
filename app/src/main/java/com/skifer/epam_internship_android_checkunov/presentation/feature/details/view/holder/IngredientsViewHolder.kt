package com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.presentation.model.IngredientModel

/**
 * Holder for ingredients of dish in details fragment
 *
 * Provides logic to display for each list item
 * @param itemView view of list item on the screen that was clicked
 */
class IngredientsViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    /**
     * Binds this holder with [IngredientModel] item
     * @param ingredientModel Model bound to this holder
     */
    fun bind(ingredientModel: IngredientModel) {
        itemView.findViewById<TextView>(R.id.ingredient_item).text =
            String.format(itemView.context.getString(R.string.ingredient),
                ingredientModel.name,
                ingredientModel.measure
            )
    }
}