package com.skifer.epam_internship_android_checkunov.list_adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R

class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var dishLabel: TextView? = null
    var dishImage: ImageView? = null

    init {
        dishLabel = itemView.findViewById(R.id.dishLabel)
        dishImage = itemView.findViewById(R.id.dishImage)
    }
}