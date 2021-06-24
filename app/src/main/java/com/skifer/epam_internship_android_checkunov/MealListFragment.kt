package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.food_types.Cuisine
import com.skifer.epam_internship_android_checkunov.food_types.FoodType
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.MealModel

/**
 * Displays a list of dishes
 */
class MealListFragment : Fragment(R.layout.fragment_meal_list), Adapter.onItemListener {

    /**The dishes list on the screen*/
    private lateinit var dishListView: RecyclerView

    /**The items list that should be on the screen. Contained in [dishListView] */
    private var dishes: List<MealModel> = someDishes()

    /**[dishListView] custom adapter*/
    private lateinit var adapter: Adapter<MealModel>

    /**
     * Sets [adapter] properties and binds it with [dishListView]
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = Adapter()
        adapter.setList(dishes)
        adapter.setItemListener(this)

        dishListView = view.findViewById(R.id.dishListView)
        dishListView.layoutManager = LinearLayoutManager(context)
        dishListView.adapter = adapter
    }

    /**
     * Generates test list. Will be removed... someday
     */
    private fun someDishes(): List<MealModel> {
        val dishes = mutableListOf<MealModel>()
        dishes.add(
                MealModel(
                        id = 0,
                        title = "Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots",
                        type = FoodType.MEAT,
                        country = Cuisine.EAST,
                        picture = R.drawable.soy_glazed_meatloaves
                )
        )
        dishes.add(
                MealModel(
                        id = 1,
                        title = "Steak Diane",
                        type = FoodType.MEAT,
                        country = Cuisine.USA,
                        picture = R.drawable.steak_diane
                )
        )
        dishes.add(
                MealModel(
                        id = 2,
                        title = "Nice and hot spicy meat",
                        type = FoodType.MEAT,
                        country = Cuisine.JAMAICAN,
                        picture = R.drawable.heheboi
                )
        )
        return dishes
    }

    /**
     * Starts new fragment the [MealDetailsFragment] with [meal] model
     * @param meal dish model
     */
    override fun onItemClick(meal: MealModel) {
        requireActivity().supportFragmentManager
            .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            .replace(
                R.id.containerHost,
                MealDetailsFragment.
                newInstance(meal)
            )
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val MEAL_LIST = "MEAL_LIST"

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance() = MealListFragment().apply {
            //arguments = bundleOf(MEAL_LIST to ...) then get arguments somewhere
        }
    }

}