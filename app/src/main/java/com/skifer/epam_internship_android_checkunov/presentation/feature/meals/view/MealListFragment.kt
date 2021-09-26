package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.di.ComponentProvider
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.MealDetailsFragment
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di.MealsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel.MealListViewModel
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SharedSettingsViewModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel
import com.skifer.epam_internship_android_checkunov.presentation.model.CategoryModel
import javax.inject.Inject

/**
 * Class of food displayed on the screen
 */
class MealListFragment : Fragment(R.layout.fragment_meal_list), ComponentProvider<MealsComponent>{

    /**The dishes list on the screen*/
    private lateinit var dishListView: RecyclerView

    /**[dishListView] custom adapter*/
    private lateinit var mealListAdapterModel: ViewHolderAdapter<MealListItemModel>

    /**The types list on the screen*/
    private lateinit var typeListView: RecyclerView

    /**[typeListView] custom adapter*/
    private lateinit var categoryListAdapter: ViewHolderAdapter<CategoryModel>

    private lateinit var itemListModel: List<MealListItemModel>

    @Inject
    lateinit var viewModel: MealListViewModel

    @Inject
    lateinit var sorterSharedView: SharedSettingsViewModel

    override val component: MealsComponent by lazy {
        MealsComponent.create(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

        initTypeListView()
        initMealListView()
        initActionBar(view)

        loadTypes()

        sorterSharedView.sortBy.observe(viewLifecycleOwner, {
            mealListAdapterModel.setList(sorterSharedView.sort(itemListModel))
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
        categoryListAdapter = ViewHolderAdapter()
        categoryListAdapter.setItemListener(object : ViewHolderAdapter.onItemListener<CategoryModel> {
            override fun onItemClick(item: CategoryModel?) {
                if (item != null) {
                    viewModel.setCategory(item.strCategory)
                }
                categoryListAdapter.notifyDataSetChanged()
            }
        })
        typeListView = requireView().findViewById(R.id.typesListView)
        typeListView.adapter = categoryListAdapter
    }

    /**
     * View components initializing
     */
    private fun initMealListView() {
        mealListAdapterModel = ViewHolderAdapter()
        mealListAdapterModel.setItemListener(object :
            ViewHolderAdapter.onItemListener<MealListItemModel> {
            override fun onItemClick(itemModel: MealListItemModel?) {
                if (itemModel != null) {
                    findNavController().navigate(
                        R.id.mealDetailsFragment,
                        bundleOf(MealDetailsFragment.MEAL_ID_INTENT to itemModel.idMeal)
                    )
                }
            }
        })
        dishListView = requireView().findViewById(R.id.dishListView)
        dishListView.adapter = mealListAdapterModel
    }

    private fun loadTypes() {
        viewModel.loadTypeList()
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                getString(R.string.error_cant_load_types),
                Toast.LENGTH_LONG
            ).show()
        }
        viewModel.categoryList.observe(viewLifecycleOwner) {
            categoryListAdapter.setList(it)
            viewModel.setCategory(it.first().strCategory)
            loadMeals()
        }
    }

    private fun loadMeals() {
        viewModel.loadMealList()
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                getString(R.string.error_cant_load_meal_list),
                Toast.LENGTH_LONG
            ).show()
        }
        viewModel.mealListModel.observe(viewLifecycleOwner) {
            itemListModel = it
            bind(it)
        }
    }

            /**
             * Binding loaded list from network with recyclerview adapter
             * @param dishesListModel loaded from network
             */
            private fun bind(dishesListModel: List<MealListItemModel>) {
                mealListAdapterModel.setList(
                    sorterSharedView.sort(
                        dishesListModel
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