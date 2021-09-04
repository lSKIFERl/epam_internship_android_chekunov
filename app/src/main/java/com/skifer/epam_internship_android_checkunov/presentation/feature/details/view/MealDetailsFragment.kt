package com.skifer.epam_internship_android_checkunov.presentation.feature.details.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.data.network.exception.MealsIsEmptyException
import com.skifer.epam_internship_android_checkunov.data.repository.MealModelRepositoryImpl
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealModelUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel.MealModelFactory
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel.MealModelViewModel
import com.skifer.epam_internship_android_checkunov.presentation.model.Ingredient
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModel
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Displays detailed information about selected dish in [MealListFragment]
 */
class MealDetailsFragment: Fragment(R.layout.fragment_meal_details) {

    /**food types list tagsAdapter*/
    private lateinit var tagsAdapter: ViewHolderAdapter<String>

    /**ingredients list tagsAdapter*/
    private lateinit var ingredientAdapter: ViewHolderAdapter<Ingredient>

    private lateinit var disposable: Disposable

    private val viewModel: MealModelViewModel by viewModels{
        MealModelFactory(
            MealModelUseCase(
                MealModelRepositoryImpl()
            ),
            arguments?.getInt("MEAL_ID_INTENT")?: error("Wrong id of dish")
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        disposable = viewModel.loadData()
        viewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                is MealsIsEmptyException ->
                    Toast.makeText(
                        context,
                        "There is nothing to see here",
                        Toast.LENGTH_LONG
                    ).show()
                is Throwable ->
                    Toast.makeText(
                        context,
                        "Error: Can't load meal model",
                        Toast.LENGTH_LONG
                    ).show()
            }
            parentFragmentManager.popBackStack()
        }
        viewModel.meal.observe(viewLifecycleOwner){
            bind(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    /**
     * View components initializing
     */
    @SuppressLint("CutPasteId")
    private fun initView(){
        tagsAdapter = ViewHolderAdapter()
        ingredientAdapter = ViewHolderAdapter()
        view?.let {
            it.findViewById<RecyclerView>(R.id.tag)?.adapter = tagsAdapter
            it.findViewById<RecyclerView>(R.id.ingredients_list)?.adapter = ingredientAdapter
            it.findViewById<Toolbar>(R.id.toolbarMealDetails).setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

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