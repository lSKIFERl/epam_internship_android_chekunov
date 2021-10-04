package com.skifer.epam_internship_android_checkunov.presentation.feature.details.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.data.network.exception.MealsIsEmptyException
import com.skifer.epam_internship_android_checkunov.di.ComponentProvider
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.di.DaggerDetailsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.di.DetailsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel.MealDetailsViewModel
import com.skifer.epam_internship_android_checkunov.presentation.model.IngredientModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModel
import javax.inject.Inject

/**
 * Displays detailed information about selected dish in main fragment
 */
class MealDetailsFragment : Fragment(R.layout.fragment_meal_details),
    ComponentProvider<DetailsComponent> {

    @Inject
    lateinit var viewModel: MealDetailsViewModel

    override val component: DetailsComponent by lazy {
        DaggerDetailsComponent.factory().create(App.instance.appComponent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        observeMeal()
    }

    private fun observeMeal() {
        val id = arguments?.getInt(MEAL_ID_INTENT) ?: error(
            getString(R.string.error_wrong_id)
        )
        viewModel.loadData(id)
        viewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                is MealsIsEmptyException ->
                    Toast.makeText(
                        context,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                is Throwable ->
                    Toast.makeText(
                        context,
                        getString(R.string.error_cant_load_meal),
                        Toast.LENGTH_LONG
                    ).show()
            }
            findNavController().popBackStack()
        }
        viewModel.meal.observe(viewLifecycleOwner) {
            bindData(it)
        }
    }

    /**
     * Binding data with view components
     * @param meal [MealModel] to bindData with components
     */
    private fun bindData(meal: MealModel?) {
        val tagsAdapter = ViewHolderAdapter<String>()
        val ingredientModelAdapter = ViewHolderAdapter<IngredientModel>()
        tagsAdapter.setList(meal?.tags)
        ingredientModelAdapter.setList(meal?.ingredientModels)

        view?.let {
            Glide
                .with(it)
                .load(
                    meal?.strMealThumb
                        ?: R.drawable.heheboi
                )
                .into(
                    requireView()
                        .findViewById(
                            R.id.detailMealImage
                        )
                )
            it.findViewById<TextView>(R.id.detailTitle).text =
                meal?.strMeal
                    ?: getString(R.string.empty)
            it.findViewById<TextView>(R.id.Cuisine).text =
                meal?.strArea
                    ?: getString(R.string.empty)
            it.findViewById<TextView>(R.id.instructions).text =
                meal?.strInstructions
                    ?: getString(R.string.empty_instructions)
            it.findViewById<RecyclerView>(R.id.tag)?.adapter =
                tagsAdapter
            it.findViewById<RecyclerView>(R.id.ingredients_list)?.adapter =
                ingredientModelAdapter
            it.findViewById<Toolbar>(R.id.toolbarMealDetails).setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    companion object {
        const val MEAL_ID_INTENT = "MEAL_ID_INTENT"

        /**
         * Should be called instead instead of just instantiating the class
         * @param dishId [MealModel] id containing information to be displayed on the screen
         */
        fun newInstance(dishId: Int) = MealDetailsFragment().apply {
            arguments = bundleOf(MEAL_ID_INTENT to dishId)
        }
    }
    }