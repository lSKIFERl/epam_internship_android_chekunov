package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.domain.entity.MealModelEntity
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModelListItem

/**
 * Holder for dish list
 *
 * Provides logic to display for each list item
 * @param itemView view of list item on the screen that was clicked
 * @param onItemListener Click listener for each list item
 */
class DishViewHolder(itemView: View, onItemListener: ViewHolderAdapter.onItemListener<MealModelListItem>)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    /**model of containment dish*/
    lateinit var meal: MealModelListItem

    /**click listener*/
    val onItemListener: ViewHolderAdapter.onItemListener<MealModelListItem>

    init {
        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
    }

    /**
     * Binds this holder with [MealModelEntity] item
     * @param meal Model bound to this holder
     */
    fun bind(meal: MealModelListItem) {
        this.meal = meal
        itemView.findViewById<TextView>(R.id.dishLabel).text = meal.strMeal
        Glide
            .with(itemView)
            .load(meal.strMealThumb)
            .into(itemView.findViewById(R.id.dishImage))
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        this.onItemListener.onItemClick(meal)
    }
}