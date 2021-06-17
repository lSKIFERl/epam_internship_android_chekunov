package com.skifer.epam_internship_android_checkunov.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.model.MealModel

class Adapter (private val items: List<MealModel>): RecyclerView.Adapter<DishViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        return DishViewHolder(
                LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.dishLabel?.text = items[position].name
        holder.dishImage?.setImageResource(items[position].picture)
    }

    override fun getItemCount(): Int = items.size
}