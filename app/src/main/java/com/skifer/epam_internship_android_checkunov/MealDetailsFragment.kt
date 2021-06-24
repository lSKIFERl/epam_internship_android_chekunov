package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.skifer.epam_internship_android_checkunov.model.MealModel

/**
 * Displays detailed information about selected dish in [MealListFragment]
 */
class MealDetailsFragment: Fragment(R.layout.fragment_meal_details) {

    /**Model containing information to display*/
    private lateinit var meal: MealModel

    /**
     * Gets [meal] from the intent arguments and binds to specific items on the screen
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meal = arguments?.getParcelable(DETAIL_INTENT)!!

        with(view) {
            findViewById<ImageView>(R.id.detailMealImage).setImageResource(meal.picture)
            findViewById<TextView>(R.id.detailTitle).text = meal.title
            findViewById<TextView>(R.id.Cuisine).text = meal.country?.name ?: "Unknown"
            findViewById<TextView>(R.id.tag).text = meal.type?.name ?: "Unknown"
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