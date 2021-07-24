package com.skifer.epam_internship_android_checkunov.list_adapter.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.TypeModel

/**
 * Holder for types of food in list fragment
 *
 * Provides logic to display for each list item
 * @param itemView view of list item on the screen that was clicked
 * @param onItemListener Click listener for each list item
 */
class TypeViewHolder(itemView: View, onItemListener: Adapter.onItemListener<TypeModel>)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    /**Contained model*/
    lateinit var type: TypeModel

    /**Click listener*/
    val onItemListener: Adapter.onItemListener<TypeModel>

    init{
        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
    }

    /**
     * Binds this holder with [TypeModel] item
     * @param type Model bound to this holder
     */
    fun bind(type: TypeModel) {
        this.type = type
        Glide
            .with(itemView)
            .load(type.strCategoryThumb)
            .into(itemView.findViewById(R.id.typeImage))
        itemView.isSelected =
            adapterPosition == App.instance.sharedPreferences.getInt("new_meal_type_id", 0)
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        App.instance.sharedPreferences
            .edit()
            .putInt("new_meal_type_id", adapterPosition)
            .apply()
        this.onItemListener.onItemClick(type)
    }
}