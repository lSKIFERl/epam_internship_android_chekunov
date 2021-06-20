package com.skifer.epam_internship_android_checkunov.list_adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.model.MealModel

class DishViewHolder(itemView: View, onItemListener: Adapter.onItemListener)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var dishLabel: TextView? = null
    var dishImage: ImageView? = null
    lateinit var meal: MealModel
    val onItemListener: Adapter.onItemListener

    init {
        dishLabel = itemView.findViewById(R.id.dishLabel)
        dishImage = itemView.findViewById(R.id.dishImage)
        this.onItemListener = onItemListener

        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        this.onItemListener.onItemClick(meal)
    }
}