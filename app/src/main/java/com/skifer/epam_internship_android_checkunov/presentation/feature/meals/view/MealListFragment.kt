package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.databinding.FragmentMealListBinding
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

    private val mealListAdapter: ViewHolderAdapter<MealListItemModel> = ViewHolderAdapter()

    private lateinit var binding: FragmentMealListBinding

    @Inject
    lateinit var viewModel: MealListViewModel

    @Inject
    lateinit var sorterSharedView: SharedSettingsViewModel

    override val component: MealsComponent by lazy {
        DaggerMealsComponent.factory().create(App.instance.appComponent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

        observeLiveData()

        initView()
    }

    private fun observeLiveData() =
        viewModel.run {
            errorLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(
                    context,
                    it,
                    Toast.LENGTH_LONG
                ).show()
            }
            categoryList.observe(viewLifecycleOwner) {
                categoryListAdapter.setList(it)
            }
            mealListModel.observe(viewLifecycleOwner) {
                bindData(it)
            }
        }

    private fun initView() {
        // Initializing toolbar
        binding.toolbarHome.apply {
            inflateMenu(R.menu.menu_host)
            setOnMenuItemClickListener {
                findNavController().navigate(
                    MealListFragmentDirections.actionMealListFragmentToSettingsFragment()
                )
                return@setOnMenuItemClickListener true
            }
        }

        // Initializing categories list
        categoryListAdapter.let {
            it.setItemListener(object :
                ViewHolderAdapter.OnItemListener<CategoryModel> {
                override fun onItemClick(item: CategoryModel) {
                    viewModel.setCategory(item)
                }
            })
            binding.categoriesListView.adapter = it
        }

        // Initializing categories list
        mealListAdapter.let {
            it.setItemListener(object :
                ViewHolderAdapter.OnItemListener<MealListItemModel> {
                override fun onItemClick(item: MealListItemModel) {
                    findNavController().navigate(
                        R.id.mealDetailsFragment,
                        bundleOf(MealDetailsFragment.MEAL_ID_INTENT to item.idMeal)
                    )
                }
            })
            binding.mealListView.adapter = it
        }
    }

    /**
     * Binding loaded list from network with recyclerview adapter
     * @param dishesListModel loaded from network
     */
    private fun bindData(dishesListModel: List<MealListItemModel>) {
        sorterSharedView.sortByData.observe(viewLifecycleOwner, {
            mealListAdapter.setList(sorterSharedView.sort(dishesListModel))
            binding.mealListView.smoothScrollToPosition(0)
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