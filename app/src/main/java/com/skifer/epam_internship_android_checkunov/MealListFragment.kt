package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.net.repository.MealModelRepository

/**
 * Class of food displayed on the screen
 */
class MealListFragment : Fragment(R.layout.fragment_meal_list), Adapter.onItemListener<MealModelListItem> {

    /**The dishes list on the screen*/
    private lateinit var dishListView: RecyclerView

    /**The items list that should be on the screen. Contained in [dishListView] */
    private lateinit var dishes: List<MealModelListItem>

    /**[dishListView] custom adapter*/
    private lateinit var adapter: Adapter<MealModelListItem>

    /**
     * Sets [adapter] properties and binds it with [dishListView]
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDishList(arguments?.getString("TYPE")?: error("Incorrect type"), view)
    }

    private fun loadDishList(type: String, view: View) = MealModelRepository.createDishList(
            type,
            caseComplete = { dishesList ->
                if (dishesList != null) {
                    this.dishes = dishesList
                    bind(view)
                } },
            caseError = { e ->
                Log.e("Net Exception", "Error: can't load dish list", e)
                Toast.makeText(context, "Error: can't load dish list", Toast.LENGTH_LONG).show()
            }
        )

    private fun bind(view: View) {
        adapter = Adapter()
        adapter.setList(dishes)
        adapter.setItemListener(this)

        dishListView = view.findViewById(R.id.dishListView)
        dishListView.layoutManager = LinearLayoutManager(context)
        dishListView.adapter = adapter
    }

    /**
     * Starts new fragment the [MealDetailsFragment] with [item] model
     * @param item dish model
     */
    override fun onItemClick(item: MealModelListItem) {
        requireActivity().supportFragmentManager
            .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            .replace(
                R.id.containerHost,
                MealDetailsFragment.
                newInstance(item.idMeal)
            )
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val TYPE = "TYPE"

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance(type: String) = MealListFragment().apply {
            //arguments = bundleOf(MEAL_LIST to ...) then get arguments somewhere
            arguments = bundleOf("TYPE" to type)
        }
    }

}