package com.skifer.epam_internship_android_checkunov

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.Ingredient
import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.net.repository.MealModelRepository

/**
 * Displays detailed information about selected dish in [MealListFragment]
 */
class MealDetailsFragment: Fragment(R.layout.fragment_meal_details) {

    /**Model containing information to display*/
    private var meal: MealModel? = null

    /**food types list tagsAdapter*/
    private lateinit var tagsAdapter: Adapter<String>

    /**ingredients list tagsAdapter*/
    private lateinit var ingredientAdapter: Adapter<Ingredient>

    /**
     * Gets [meal] from the intent arguments and binds to specific items on the screen
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDishDetails(arguments?.getInt("DETAIL_INTENT")?: error("Wrong id of dish"), view)
    }

    private fun loadDishDetails(id: Int, view: View) = MealModelRepository.createDishDetails(
            id,
            caseComplete = { mealModel ->
                meal = mealModel
                bind(view) },
            caseError = {e ->
                Log.e("Net", "Error: Can't load meal model", e)
                Toast.makeText(context, "Error: Can't load meal model", Toast.LENGTH_LONG).show()
            }
        )

    @SuppressLint("CutPasteId")
    private fun bind(view: View) {
        tagsAdapter = Adapter()
        tagsAdapter.setList(meal?.strTags)

        ingredientAdapter = Adapter()
        ingredientAdapter.setList(meal?.ingredients)

        with(view) {
            Glide
                    .with(this)
                    .load(meal?.strMealThumb?: R.drawable.heheboi)
                    .into(findViewById(R.id.detailMealImage))
            findViewById<TextView>(R.id.detailTitle).text = meal?.strMeal ?: "Unknown"
            findViewById<TextView>(R.id.Cuisine).text = meal?.strArea?: "Unknown"
            findViewById<TextView>(R.id.instructions).text = meal?.strInstructions?: "No instructions"
            findViewById<RecyclerView>(R.id.tag).layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
            )
            findViewById<RecyclerView>(R.id.tag).adapter = tagsAdapter
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
        fun newInstance(idDish: Int) = MealDetailsFragment().apply {
            arguments = bundleOf(DETAIL_INTENT to idDish)
        }
    }

}