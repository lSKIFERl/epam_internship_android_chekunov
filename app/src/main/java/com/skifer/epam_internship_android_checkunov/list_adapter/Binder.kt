package com.skifer.epam_internship_android_checkunov.list_adapter

import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.list_adapter.holders.DishViewHolder
import com.skifer.epam_internship_android_checkunov.list_adapter.holders.TypeViewHolder
import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.TypeModel

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
                holder.meal = item
            }
            is TypeViewHolder -> {
                item as TypeModel
                holder.typeImage.setImageResource(item.picture)
                holder.type = item
            }
        }
    }
}