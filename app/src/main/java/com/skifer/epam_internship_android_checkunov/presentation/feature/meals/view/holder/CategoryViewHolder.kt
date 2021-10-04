package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.model.CategoryModel

/**
 * Holder for types of food in list fragment
 *
 * Provides logic to display for each list item
 * @param itemView view of list item on the screen that was clicked
 * @param OnItemListener Click listener for each list item
 */
class CategoryViewHolder(
    itemView: View,
    private val OnItemListener: ViewHolderAdapter.OnItemListener<CategoryModel>
)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    /**Contained model*/
    private lateinit var category: CategoryModel

    init{
        itemView.setOnClickListener(this)
    }

    /**
     * Binds this holder with [CategoryModel] item
     * @param category Model bound to this holder
     */
    fun bind(category: CategoryModel) {
        this.category = category
        Glide
            .with(itemView)
            .load(category.strCategoryThumb)
            .into(itemView.findViewById(R.id.categoryImage))
        itemView.isSelected = category.active
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        this.OnItemListener.onItemClick(category)
    }
}