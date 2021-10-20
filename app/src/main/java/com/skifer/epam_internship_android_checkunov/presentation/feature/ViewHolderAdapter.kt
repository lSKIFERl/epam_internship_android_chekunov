package com.skifer.epam_internship_android_checkunov.presentation.feature

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.domain.entity.MealEntity
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter.OnItemListener
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.holder.FoodTypeHolder
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.holder.IngredientsViewHolder
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.holder.CategoryViewHolder
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.holder.MealViewHolder
import com.skifer.epam_internship_android_checkunov.presentation.model.CategoryModel
import com.skifer.epam_internship_android_checkunov.presentation.model.IngredientModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel

/**
 * List Adapter class for recyclerview.
 *
 * NOTE: recyclerview may have empty list and haven't click listener for items of list.
 *
 * So set [List] and [OnItemListener] by [setList] and [setItemListener] if necessary
 * @param T model class
 */
class ViewHolderAdapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**Item listener for list [items]*/
    private lateinit var itemListener: OnItemListener<T>

    /**List of items on the screen */
    private var items: MutableSet<T> = mutableSetOf()

    /**Callable view context*/
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder? = null
        context = parent.context
        when(items.elementAtOrNull(0)) {
            is MealListItemModel -> holder = MealViewHolder(
                    LayoutInflater.from(context).inflate(
                            R.layout.item_meal,
                            parent,
                            false),
                    itemListener as OnItemListener<MealListItemModel>
            )
            is CategoryModel -> holder = CategoryViewHolder(
                    LayoutInflater.from(context).inflate(
                            R.layout.item_categories,
                            parent,
                            false),
                    itemListener as OnItemListener<CategoryModel>
                    )
            is String -> holder = FoodTypeHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_tag,
                    parent,
                    false
                )
            )
            is IngredientModel -> holder = IngredientsViewHolder (
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_ingredient,
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
     * [MealViewHolder], [CategoryViewHolder], [FoodTypeHolder], [IngredientsViewHolder]
     * @param item item that can be connected to the specific Holder:
     * [MealEntity], [CategoryModel], [String], [IngredientModel]
     */
    private fun <T> RecyclerView.ViewHolder.bind(item: T?) {
        when(this) {
            is MealViewHolder -> (item as? MealListItemModel)?.let{ bind(it) }
            is CategoryViewHolder -> (item as? CategoryModel)?.let{ bind(it) }
            is FoodTypeHolder -> (item as? String)?.let { bind(it) }
            is IngredientsViewHolder -> (item as? IngredientModel)?.let { bind(it) }
        }
    }

    /**
     * Sets list of items
     */
    fun setList(list: List<T>?): ViewHolderAdapter<T> {
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
        return this
    }

    /**
     * Sets listener for each item of recyclerview
     */
    fun setItemListener(itemListener: OnItemListener<T>) {
        itemListener.also { this.itemListener = it }
    }

    /**
     * Click listener for each item of recyclerview
     */
    interface OnItemListener <T> {
        fun onItemClick(item: T)
    }
}
