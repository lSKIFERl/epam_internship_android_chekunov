package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.di.ComponentProvider
import com.skifer.epam_internship_android_checkunov.presentation.base.ShareViewModel
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.MealDetailsFragment
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di.MealsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel.MealListViewModel
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.view.SettingsFragment
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.presentation.model.TypeModel
import javax.inject.Inject

/**
 * Class of food displayed on the screen
 */
class MealListFragment : Fragment(R.layout.fragment_meal_list), ComponentProvider<MealsComponent>{

    /**The dishes list on the screen*/
    private lateinit var dishListView: RecyclerView

    /**[dishListView] custom adapter*/
    private lateinit var mealListAdapter: ViewHolderAdapter<MealModelListItem>

    /**The types list on the screen*/
    private lateinit var typeListView: RecyclerView

    /**[typeListView] custom adapter*/
    private lateinit var typeListAdapter: ViewHolderAdapter<TypeModel>

    private lateinit var itemList: List<MealModelListItem>

    private lateinit var sharedPreference: SharedPreferences

    private val sorterSharedView: ShareViewModel by activityViewModels()

    @Inject
    lateinit var viewModel: MealListViewModel

    override val component: MealsComponent by lazy {
        MealsComponent.create(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference = App.instance.sharedPreferences
        component.inject(this)

        loadTypes()

        initActionBar(view)
        initTypeListView()
        initMealListView()

        sorterSharedView.sortBy.observe(viewLifecycleOwner, {
            mealListAdapter.setList(sorterSharedView.sort(itemList, it))
        })
    }

    private fun initActionBar(view: View) {
        val actionBarToolBar: Toolbar = view.findViewById(R.id.toolbar_home) as Toolbar
        actionBarToolBar.inflateMenu(R.menu.menu_host)
        actionBarToolBar.setOnMenuItemClickListener {
            findNavController().navigate(
                MealListFragmentDirections.actionMealListFragmentToSettingsFragment()
            )
            return@setOnMenuItemClickListener true
        }
    }

    private fun initTypeListView() {
        typeListAdapter = ViewHolderAdapter()
        typeListAdapter.setItemListener(object : ViewHolderAdapter.onItemListener<TypeModel> {
            override fun onItemClick(item: TypeModel?) {
                sharedPreference
                    .edit()
                    ?.putString(TYPE, item?.strCategory)
                    ?.apply()
                if (item != null) {
                    viewModel.loadMealList(item.strCategory)
                }
                typeListAdapter.notifyDataSetChanged()
            }
        })
        typeListView = requireView().findViewById(R.id.typesListView)
        typeListView.adapter = typeListAdapter
    }

    /**
     * View components initializing
     */
    private fun initMealListView() {
        mealListAdapter = ViewHolderAdapter()
        mealListAdapter.setItemListener(object :
            ViewHolderAdapter.onItemListener<MealModelListItem> {
            override fun onItemClick(item: MealModelListItem?) {
                if (item != null) {
                    findNavController().navigate(
                        R.id.mealDetailsFragment,
                        bundleOf(MealDetailsFragment.MEAL_ID_INTENT to item.idMeal)
                    )
                }
            }
        })
        dishListView = requireView().findViewById(R.id.dishListView)
        dishListView.adapter = mealListAdapter
    }

    private fun loadTypes() {
        viewModel.loadTypeList()
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                "Can't load types",
                Toast.LENGTH_LONG
            ).show()
        }
        viewModel.typeList.observe(viewLifecycleOwner) {
            typeListAdapter.setList(it)
            defaultLoad(it.first().strCategory)
            loadMeals(sharedPreference.getString(TYPE, null))
        }
    }

    private fun loadMeals(category: String?) {
        if (category != null) {
            viewModel.loadMealList(category)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                "Error: can't load dish list",
                Toast.LENGTH_LONG
            ).show()
        }
        viewModel.mealList.observe(viewLifecycleOwner) {
            itemList = it
            bind(it)
        }
    }

        /**
         * Set first type as default for the first list launching or loading default value
         * @param type first loaded type
         */
        private fun defaultLoad(type: String) {
            if (sharedPreference
                    .getString(TYPE, null) == null
            ) {
                sharedPreference
                    .edit()
                    ?.putString(TYPE, type)
                    ?.apply()
            }
        }

            /**
             * Binding loaded list from network with recyclerview adapter
             * @param dishesList loaded from network
             */
            private fun bind(dishesList: List<MealModelListItem>) {
                mealListAdapter.setList(
                    sorterSharedView.sort(
                        dishesList,
                        App.instance.sharedPreferences.getString(
                            SettingsFragment.SORT_MEALS_LIST,
                            sorterSharedView.SORT_ASC
                        )
                    )
                )
            }

            companion object {
                const val TYPE = "TYPE"

                /**
                 * Should be called instead instead of just instantiating the class
                 */
                fun newInstance(type: String) = MealListFragment().apply {
                    //arguments = bundleOf(MEAL_LIST to ...) then get arguments somewhere
                    arguments = bundleOf(TYPE to type)
                }
            }
}