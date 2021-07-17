package com.skifer.epam_internship_android_checkunov.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.Ingredient
import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.net.repository.MealModelRepository

/**
 * Displays detailed information about selected dish in [MealListFragment]
 */
class MealDetailsFragment: Fragment(R.layout.fragment_meal_details) {

    /**food types list tagsAdapter*/
    private lateinit var tagsAdapter: Adapter<String>

    /**ingredients list tagsAdapter*/
    private lateinit var ingredientAdapter: Adapter<Ingredient>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadDishDetails(arguments?.getInt("MEAL_ID_INTENT")?: error("Wrong id of dish"))
    }

    /**
     * View components initializing
     */
    @SuppressLint("CutPasteId")
    private fun initView(){
        tagsAdapter = Adapter()
        ingredientAdapter = Adapter()
        view?.let {
            it.findViewById<RecyclerView>(R.id.tag)?.adapter = tagsAdapter
            it.findViewById<RecyclerView>(R.id.ingredients_list)?.adapter = ingredientAdapter
        }
    }

    /**
     * Loading data from network
     * @param id Id of meal in API
     */
    private fun loadDishDetails(id: Int) = MealModelRepository.createDishDetails(
            id,
            caseComplete = { mealModel ->
                if (mealModel != null) {
                    bind(mealModel)
                }
            },
            caseError = {e ->
                Log.e("Net", "Error: Can't load meal model", e)
                Toast.makeText(context, "Error: Can't load meal model", Toast.LENGTH_LONG).show()
            }
        )

    /**
     * Binding data with view components
     * @param meal [MealModel] to bind with components
     */
    private fun bind(meal: MealModel?) {
        tagsAdapter.setList(meal?.tags)
        ingredientAdapter.setList(meal?.ingredients)

        view?.let {
            Glide
                    .with(it)
                    .load(meal?.strMealThumb ?: R.drawable.heheboi)
                    .into(requireView().findViewById(R.id.detailMealImage))
            it.findViewById<TextView>(R.id.detailTitle).text = meal?.strMeal ?: "Unknown"
            it.findViewById<TextView>(R.id.Cuisine).text = meal?.strArea ?: "Unknown"
            it.findViewById<TextView>(R.id.instructions).text = meal?.strInstructions ?: "No instructions"
        }
    }

    companion object {
        private const val MEAL_ID_INTENT = "MEAL_ID_INTENT"
        /**
         * Should be called instead instead of just instantiating the class
         * @param dishId [MealModel] id containing information to be displayed on the screen
         */
        fun newInstance(dishId: Int) = MealDetailsFragment().apply {
            arguments = bundleOf(MEAL_ID_INTENT to dishId)
        }
    }

}