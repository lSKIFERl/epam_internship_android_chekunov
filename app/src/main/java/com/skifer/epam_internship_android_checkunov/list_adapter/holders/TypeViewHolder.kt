package com.skifer.epam_internship_android_checkunov.list_adapter.holders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
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

    /**Icon of the list item*/
    val typeImage: ImageView

    /**Contained model*/
    lateinit var type: TypeModel

    /**Click listener*/
    val onItemListener: Adapter.onItemListener<TypeModel>

    init{
        typeImage = itemView.findViewById(R.id.typeImage)

        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        this.onItemListener.onItemClick(type)
    }
}