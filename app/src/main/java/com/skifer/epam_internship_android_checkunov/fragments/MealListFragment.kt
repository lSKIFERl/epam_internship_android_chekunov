package com.skifer.epam_internship_android_checkunov.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.activities.FragmentsCommunicate
import com.skifer.epam_internship_android_checkunov.data.net.repository.MealListRepositoryImpl
import com.skifer.epam_internship_android_checkunov.fragments.viewmodel.MealListFactory
import com.skifer.epam_internship_android_checkunov.fragments.viewmodel.MealListViewModel
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Class of food displayed on the screen
 */
class MealListFragment : Fragment(R.layout.fragment_meal_list), Adapter.onItemListener<MealModelListItem>, FragmentsCommunicate {

    /**The dishes list on the screen*/
    private lateinit var dishListView: RecyclerView

    /**[dishListView] custom adapter*/
    private lateinit var adapter: Adapter<MealModelListItem>

    private lateinit var itemList: List<MealModelListItem>

    private lateinit var disposable: Disposable

    private val viewModel: MealListViewModel by viewModels{ MealListFactory(MealListRepositoryImpl(), arguments?.getString("TYPE")?: error("Incorrect type")) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        disposable = viewModel.loadData()
        viewModel.mealList.observe(viewLifecycleOwner) {
            try {
                itemList = it
                bind(it)
            } catch (t: Throwable) {
                Toast.makeText(
                    context,
                    "Error: can't load dish list",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    /**
     * View components initializing
     */
    private fun initView() {
        adapter = Adapter()
        adapter.setItemListener(this)
        dishListView = requireView().findViewById(R.id.dishListView)
        dishListView.adapter = adapter
    }

    /**
     * Binding loaded list from network with recyclerview adapter
     * @param dishesList loaded from network
     */
    private fun bind(dishesList: List<MealModelListItem>) {
        if (App.instance.sharedPreferences.getString(
                SettingsFragment.SORT_MEALS_LIST,
                "SORT_ASC") == "SORT_ASC") {
                    adapter.setList(dishesList.sortedBy { it.strMeal })
        } else {
            adapter.setList(dishesList.sortedByDescending { it.strMeal })
        }
    }

    /**
     * Starts new fragment the [MealDetailsFragment] with [item] model
     * @param item dish model
     */
    override fun onItemClick(item: MealModelListItem) {
        requireActivity().supportFragmentManager
            .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
            .replace(
                R.id.containerHost,
                MealDetailsFragment.newInstance(item.idMeal)
            )
            .addToBackStack("meal_list")
            .commit()
    }

    companion object {
        private const val TYPE = "TYPE"
        const val TAG = "MEAL_LIST"

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance(type: String) = MealListFragment().apply {
            //arguments = bundleOf(MEAL_LIST to ...) then get arguments somewhere
            arguments = bundleOf("TYPE" to type)
        }
    }

    override fun update() {
        bind(itemList)
        adapter.notifyDataSetChanged()
    }

}