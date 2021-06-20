package com.skifer.epam_internship_android_checkunov

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.skifer.epam_internship_android_checkunov.model.MealModel

class MealDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val meal = intent.getParcelableExtra<MealModel>("DETAIL_INTENT")
        setContentView(R.layout.meal_details_activity)
        findViewById<ImageView>(R.id.detailMealImage).setImageResource(meal?.picture!!)
        findViewById<TextView>(R.id.detailTitle).text = meal.name
        findViewById<TextView>(R.id.Cuisine).text = meal.country?.name ?: "Unknown"
        findViewById<TextView>(R.id.tag).text = meal.type?.name ?: "Unknown"
    }

    companion object {
        private const val DETAIL_INTENT = "DETAIL_INTENT"
        fun getIntent(context: Context, meal: MealModel): Intent =
                Intent(context, MealDetailsActivity::class.java).putExtra(DETAIL_INTENT, meal)
    }

}