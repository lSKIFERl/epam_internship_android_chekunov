package com.skifer.epam_internship_android_checkunov.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.net.repository.MealModelRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Class of food displayed on the screen
 */
class MealListFragment : Fragment(R.layout.fragment_meal_list), Adapter.onItemListener<MealModelListItem> {

    /**The dishes list on the screen*/
    private lateinit var dishListView: RecyclerView

    /**[dishListView] custom adapter*/
    private lateinit var adapter: Adapter<MealModelListItem>

    /**used to unsubscribe from observable*/
    private var disposable: Disposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        disposable = loadDishList(
            arguments
                ?.getString(TYPE)
                ?: error("Incorrect type")
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
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
     * Loading data from network
     * @param type Meal category to load
     */
    private fun loadDishList(type: String) =
        MealModelRepository
            .createDishList(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
        { dishesList ->
            if (dishesList != null) {
                bind(dishesList)
            }
        },
        { e ->
            Log.e(
                "Net Exception",
                "Error: can't load dish list",
                e
            )
            Toast.makeText(
                context,
                "Error: can't load dish list",
                Toast.LENGTH_LONG
            ).show()
        }
    )

    /**
     * Binding loaded list from network with recyclerview adapter
     * @param dishesList loaded from network
     */
    private fun bind(dishesList: List<MealModelListItem>) {
        adapter.setList(dishesList)
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
                    MealDetailsFragment.newInstance(item.idMeal)
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