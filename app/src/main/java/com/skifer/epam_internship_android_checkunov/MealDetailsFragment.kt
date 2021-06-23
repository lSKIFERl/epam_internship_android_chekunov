package com.skifer.epam_internship_android_checkunov

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.skifer.epam_internship_android_checkunov.model.MealModel

class MealDetailsFragment: Fragment(R.layout.fragment_meal_details) {

    private lateinit var meal: MealModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meal = arguments?.getParcelable<MealModel>(DETAIL_INTENT)!!

        with(view) {
            findViewById<ImageView>(R.id.detailMealImage).setImageResource(meal?.picture!!)
            findViewById<TextView>(R.id.detailTitle).text = meal.name
            findViewById<TextView>(R.id.Cuisine).text = meal.country?.name ?: "Unknown"
            findViewById<TextView>(R.id.tag).text = meal.type?.name ?: "Unknown"
        }
    }

    companion object {
        private const val DETAIL_INTENT = "DETAIL_INTENT"
        fun newInstance(dish: MealModel) = MealDetailsFragment().apply {
            arguments = bundleOf(DETAIL_INTENT to dish)
        }
    }

}