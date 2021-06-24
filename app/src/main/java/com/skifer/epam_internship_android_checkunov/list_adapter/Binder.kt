package com.skifer.epam_internship_android_checkunov.list_adapter

import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.model.MealModel

/**
 * Class for binding
 */
object Binder {
    /**
     * Binds some holders with item
     * @param holder holder which can be one of the following: [DishViewHolder]
     * @param item item that can be connected to the specific Holder: [MealModel]
     */
    fun <T> bind(holder: RecyclerView.ViewHolder, item: T) {
        when(holder) {
            is DishViewHolder -> {
                item as MealModel
                holder.dishLabel?.text = item.title
                holder.dishImage?.setImageResource(item.picture)
                holder.meal = item}
        }
    }
}