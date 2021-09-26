package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.domain.entity.MealEntity
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel

/**
 * Holder for dish list
 *
 * Provides logic to display for each list item
 * @param itemView view of list item on the screen that was clicked
 * @param onItemModelListener Click listener for each list item
 */
class DishViewHolder(itemView: View, onItemModelListener: ViewHolderAdapter.onItemListener<MealListItemModel>)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    /**model of containment dish*/
    var mealModel: MealListItemModel? = null

    /**click listener*/
    val onItemModelListener: ViewHolderAdapter.onItemListener<MealListItemModel>

    init {
        this.onItemModelListener = onItemModelListener
        itemView.setOnClickListener(this)
    }

    /**
     * Binds this holder with [MealEntity] item
     * @param mealModel Model bound to this holder
     */
    fun bind(mealModel: MealListItemModel) {
        this.mealModel = mealModel
        itemView.findViewById<TextView>(R.id.dishLabel).text = mealModel.strMeal
        Glide
            .with(itemView)
            .load(mealModel.strMealThumb)
            .into(itemView.findViewById(R.id.dishImage))
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        this.onItemModelListener.onItemClick(mealModel)
    }
}