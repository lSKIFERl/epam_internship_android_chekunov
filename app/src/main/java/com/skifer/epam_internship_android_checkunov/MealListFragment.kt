package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.MealModel

/**
 * Class of food displayed on the screen
 */
class MealListFragment : Fragment(R.layout.fragment_meal_list), Adapter.onItemListener<MealModel> {

    /**The dishes list on the screen*/
    private lateinit var dishListView: RecyclerView

    /**The items list that should be on the screen. Contained in [dishListView] */
    private lateinit var dishes: List<MealModel>

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
     * Starts new fragment the [MealDetailsFragment] with [item] model
     * @param item dish model
     */
    override fun onItemClick(item: MealModel) {
        requireActivity().supportFragmentManager
            .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            .replace(
                R.id.containerHost,
                MealDetailsFragment.
                newInstance(item)
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