package com.skifer.epam_internship_android_checkunov.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter.onItemListener
import com.skifer.epam_internship_android_checkunov.list_adapter.holders.DishViewHolder
import com.skifer.epam_internship_android_checkunov.list_adapter.holders.TypeViewHolder
import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.TypeModel

/**
 * List Adapter class for recyclerview.
 *
 * NOTE: recyclerview may have empty list and haven't click listener for items of list.
 *
 * So set [List] and [onItemListener] by [setList] and [setItemListener] if necessary
 * @param T model class
 */
class Adapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**Item listener for list [items]*/
    private lateinit var itemListener: onItemListener<T>

    /**List of items on the screen */
    private var items: MutableSet<T> = mutableSetOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder? = null
        when(items.elementAtOrNull(0)) {
            is MealModel -> holder = DishViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.recyclerview_item,
                            parent,
                            false),
                    itemListener as onItemListener<MealModel>
            )
            is TypeModel -> holder = TypeViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.type_item,
                            parent,
                            false),
                    itemListener as onItemListener<TypeModel>
                    )
        }
        return holder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Binder.bind(holder, items.elementAtOrNull(position))
    }

    override fun getItemCount(): Int = items.size

    /**
     * Sets list of items
     */
    fun setList(list: List<T>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    /**
     * Sets listener for each item of recyclerview
     */
    fun setItemListener(itemListener: onItemListener<T>) {
        itemListener.also { this.itemListener = it }
    }

    /**
     * Click listener for each item of recyclerview
     */
    interface onItemListener <T> {
        fun onItemClick(item: T)
    }
}