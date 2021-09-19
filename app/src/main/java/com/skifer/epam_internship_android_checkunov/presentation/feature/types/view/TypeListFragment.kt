package com.skifer.epam_internship_android_checkunov.presentation.feature.types.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.data.repository.TypeModelRepositoryImpl
import com.skifer.epam_internship_android_checkunov.domain.usecase.TypeListUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.MealListFragment
import com.skifer.epam_internship_android_checkunov.presentation.feature.types.viewmodel.TypeListFactory
import com.skifer.epam_internship_android_checkunov.presentation.feature.types.viewmodel.TypeListViewModel
import com.skifer.epam_internship_android_checkunov.presentation.model.TypeModel
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Class of types of food displayed on the screen
 */
class TypeListFragment: Fragment(R.layout.fragment_type_list), ViewHolderAdapter.onItemListener<TypeModel> {

    /**The types list on the screen*/
    private lateinit var typeListView: RecyclerView

    /**[typeListView] custom adapter*/
    private lateinit var adapter: ViewHolderAdapter<TypeModel>

    private lateinit var disposable: Disposable

    private lateinit var sharedPreferences: SharedPreferences

    val viewModel: TypeListViewModel by viewModels {
        TypeListFactory(
            TypeListUseCase(
                TypeModelRepositoryImpl()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = App.instance.sharedPreferences
        initView()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun initView() {
        adapter = ViewHolderAdapter()
        adapter.setItemListener(this)
        typeListView = requireView().findViewById(R.id.typesListView)
        typeListView.adapter = adapter
    }

    /**
     * Set first type as default for the first list launching
     * @param type first loaded type
     */
    private fun saveAsDefault(type: String) {
        val isLastMealTypeNull =
            sharedPreferences.getString(LAST_MEAL_TYPE, null) == null
        if (isLastMealTypeNull) {
            saveLastType(type)
        }
    }

    private fun startMealListFragment () {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.meal_list_container,
                MealListFragment.newInstance(
                    sharedPreferences
                        .getString(LAST_MEAL_TYPE, null)
                        ?: error("Incorrect type")
                )
            )
            .addToBackStack(MealListFragment.TAG)
            .commit()
    }

    private fun observeViewModel() {
        disposable = viewModel.loadData()
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                "Can't load types",
                Toast.LENGTH_LONG
            ).show()
        }
        viewModel.typeList.observe(viewLifecycleOwner) {
            adapter.setList(it)
            saveAsDefault(it.first().strCategory)
            startMealListFragment()
        }
    }

    /**
     * Called when item clicked. Loading selected category
     * @param item selected item
     */
    override fun onItemClick(item: TypeModel) {
        adapter.notifyDataSetChanged()
        saveLastType(item.strCategory)
        startMealListFragment()
    }

    private fun saveLastType(type: String) {
        sharedPreferences
            .edit()
            ?.putString(LAST_MEAL_TYPE, type)
            ?.apply()
    }

    companion object {
        private const val TYPE_LIST = "TYPE_LIST"
        private const val LAST_MEAL_TYPE = "last_meal_type"
        const val TAG = "TYPE_LIST"

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance() = TypeListFragment().apply {
            //arguments = bundleOf(TYPE_LIST to ...) then get arguments somewhere
        }
    }
}