package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.di.ComponentProvider
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.MealDetailsFragment
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di.DaggerMealsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di.MealsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel.MealListViewModel
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SharedSettingsViewModel
import com.skifer.epam_internship_android_checkunov.presentation.model.CategoryModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel
import javax.inject.Inject

/**
 * Class of food displayed on the screen
 */
class MealListFragment : Fragment(R.layout.fragment_meal_list), ComponentProvider<MealsComponent> {

    private val categoryListAdapter: ViewHolderAdapter<CategoryModel> = ViewHolderAdapter()

    private val mealListAdapterModel: ViewHolderAdapter<MealListItemModel> = ViewHolderAdapter()

    @Inject
    lateinit var viewModel: MealListViewModel

    @Inject
    lateinit var sorterSharedView: SharedSettingsViewModel

    override val component: MealsComponent by lazy {
        DaggerMealsComponent.factory().create(App.instance.appComponent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        initCategoryListView()
        initMealListView()
        initActionBar(view)

        observeCategories()
        observeMeals()

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

    private fun initCategoryListView() {
        categoryListAdapter.setItemListener(object :
            ViewHolderAdapter.OnItemListener<CategoryModel> {
            override fun onItemClick(item: CategoryModel) {
                viewModel.setCategory(item)
            }
        })
        requireView().findViewById<RecyclerView>(R.id.categoriesListView)
            .adapter = categoryListAdapter
    }

    /**
     * View components initializing
     */
    private fun initMealListView() {
        mealListAdapterModel.setItemListener(object :
            ViewHolderAdapter.OnItemListener<MealListItemModel> {
            override fun onItemClick(item: MealListItemModel) {
                findNavController().navigate(
                    R.id.mealDetailsFragment,
                    bundleOf(MealDetailsFragment.MEAL_ID_INTENT to item.idMeal)
                )
            }
        })
        requireView().findViewById<RecyclerView>(R.id.dishListView)
            .adapter = mealListAdapterModel
    }

    private fun observeCategories() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                getString(R.string.error_cant_load_categories),
                Toast.LENGTH_LONG
            ).show()
        }
        viewModel.categoryList.observe(viewLifecycleOwner) {
            categoryListAdapter.setList(it)
        }
    }

    private fun observeMeals() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                getString(R.string.error_cant_load_meal_list),
                Toast.LENGTH_LONG
            ).show()
        }
        viewModel.mealListModel.observe(viewLifecycleOwner) {
            bindData(it)
        }
    }

    /**
     * Binding loaded list from network with recyclerview adapter
     * @param dishesListModel loaded from network
     */
    private fun bindData(dishesListModel: List<MealListItemModel>) {
        sorterSharedView.sortBy.observe(viewLifecycleOwner, {
            mealListAdapterModel.setList(sorterSharedView.sort(dishesListModel))
        })
    }

    companion object {
        const val TYPE = "CATEGORY"

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance(type: String) = MealListFragment().apply {
            //arguments = bundleOf(MEAL_LIST to ...) then get arguments somewhere
            arguments = bundleOf(TYPE to type)
        }
    }
}