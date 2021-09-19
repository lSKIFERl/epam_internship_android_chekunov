package com.skifer.epam_internship_android_checkunov.presentation.feature

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.domain.entity.MealModelEntity
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter.onItemListener
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.holder.FoodTypeHolder
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.holder.IngredientsViewHolder
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.holder.DishViewHolder
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.holder.TypeViewHolder
import com.skifer.epam_internship_android_checkunov.presentation.model.Ingredient
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.presentation.model.TypeModel

/**
 * List Adapter class for recyclerview.
 *
 * NOTE: recyclerview may have empty list and haven't click listener for items of list.
 *
 * So set [List] and [onItemListener] by [setList] and [setItemListener] if necessary
 * @param T model class
 */
class ViewHolderAdapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**Item listener for list [items]*/
    private lateinit var itemListener: onItemListener<T>

    /**List of items on the screen */
    private var items: MutableSet<T> = mutableSetOf()

    /**Callable view context*/
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder? = null
        context = parent.context
        when(items.elementAtOrNull(0)) {
            is MealModelListItem -> holder = DishViewHolder(
                    LayoutInflater.from(context).inflate(
                            R.layout.recyclerview_item,
                            parent,
                            false),
                    itemListener as onItemListener<MealModelListItem>
            )
            is TypeModel -> holder = TypeViewHolder(
                    LayoutInflater.from(context).inflate(
                            R.layout.type_item,
                            parent,
                            false),
                    itemListener as onItemListener<TypeModel>
                    )
            is String -> holder = FoodTypeHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_tag,
                    parent,
                    false
                )
            )
            is Ingredient -> holder = IngredientsViewHolder (
                LayoutInflater.from(parent.context).inflate(
                    R.layout.ingredient_item,
                    parent,
                    false
                )
            )
        }
        return holder?: error(
                Toast.makeText(
                    context,
                    "Error: unknown holder type",
                    Toast.LENGTH_LONG
            ).show()
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.bind(items.elementAtOrNull(position))
    }

    override fun getItemCount(): Int = items.size

    /**
     * Binds some holders with item
     *
     * holder can be one of the following:
     * [DishViewHolder], [TypeViewHolder], [FoodTypeHolder], [IngredientsViewHolder]
     * @param item item that can be connected to the specific Holder:
     * [MealModelEntity], [TypeModel], [FoodType], [Ingredient]
     */
    private fun <T> RecyclerView.ViewHolder.bind(item: T?) {
        when(this) {
            is DishViewHolder -> (item as? MealModelListItem)?.let{ bind(it) }
            is TypeViewHolder -> (item as? TypeModel)?.let{ bind(it) }
            is FoodTypeHolder -> (item as? String)?.let { bind(it) }
            is IngredientsViewHolder -> (item as? Ingredient)?.let { bind(it) }
        }
    }

    /**
     * Sets list of items
     */
    fun setList(list: List<T>?) {
        items.clear()
        if (list != null) {
            items.addAll(list)
        } else {
            Toast.makeText(
                    context,
                    "Error while updating the list",
                    Toast.LENGTH_LONG
            ).show()
        }
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
