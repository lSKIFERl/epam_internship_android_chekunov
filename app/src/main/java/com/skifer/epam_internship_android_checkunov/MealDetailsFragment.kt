package com.skifer.epam_internship_android_checkunov

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.food_types.FoodType
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.Ingredient
import com.skifer.epam_internship_android_checkunov.model.MealModel

/**
 * Displays detailed information about selected dish in [MealListFragment]
 */
class MealDetailsFragment: Fragment(R.layout.fragment_meal_details) {

    /**Model containing information to display*/
    private lateinit var meal: MealModel

    /**food types list adapter*/
    private lateinit var adapter: Adapter<FoodType>

    /**ingredients list adapter*/
    private lateinit var ingredientAdapter: Adapter<Ingredient>

    /**
     * Gets [meal] from the intent arguments and binds to specific items on the screen
     */
    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meal = arguments?.getParcelable(DETAIL_INTENT)!!
        adapter = Adapter()
        adapter.setList(meal.type)

        ingredientAdapter = Adapter()
        ingredientAdapter.setList(meal.ingredients)

        with(view) {
            findViewById<ImageView>(R.id.detailMealImage).setImageResource(meal.picture)
            findViewById<TextView>(R.id.detailTitle).text = meal.title
            findViewById<TextView>(R.id.Cuisine).text = meal.country?.name ?: "Unknown"
            findViewById<RecyclerView>(R.id.tag).layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            findViewById<RecyclerView>(R.id.tag).adapter = adapter
            findViewById<RecyclerView>(R.id.ingredients_list).layoutManager = LinearLayoutManager(
                context
            )
            findViewById<RecyclerView>(R.id.ingredients_list).adapter = ingredientAdapter
        }



    }


    companion object {
        private const val DETAIL_INTENT = "DETAIL_INTENT"
        /**
         * Should be called instead instead of just instantiating the class
         * @param dish [MealModel] containing information to be displayed on the screen
         */
        fun newInstance(dish: MealModel) = MealDetailsFragment().apply {
            arguments = bundleOf(DETAIL_INTENT to dish)
        }
    }

}