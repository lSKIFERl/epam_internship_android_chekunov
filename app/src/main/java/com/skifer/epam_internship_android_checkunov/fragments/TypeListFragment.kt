package com.skifer.epam_internship_android_checkunov.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.data.net.repository.TypeModelRepositoryImpl
import com.skifer.epam_internship_android_checkunov.fragments.viewmodel.TypeListFactory
import com.skifer.epam_internship_android_checkunov.fragments.viewmodel.TypeListViewModel
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.TypeModel
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Class of types of food displayed on the screen
 */
class TypeListFragment: Fragment(R.layout.fragment_type_list), Adapter.onItemListener<TypeModel> {

    /**The types list on the screen*/
    private lateinit var typeListView: RecyclerView

    /**[typeListView] custom adapter*/
    private lateinit var adapter: Adapter<TypeModel>

    private lateinit var disposable: Disposable

    private lateinit var sharedPreference: SharedPreferences

    val viewModel: TypeListViewModel by viewModels { TypeListFactory(TypeModelRepositoryImpl()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference = App.instance.sharedPreferences
        initView()
        disposable = viewModel.loadData()
        viewModel.typeList.observe(viewLifecycleOwner) {
            try {
                adapter.setList(it)
                defaultLoad(it.first().strCategory)
            } catch (t: Throwable) {
                Toast.makeText(
                    context,
                    "Can't load types",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        startMealListFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun initView() {
        adapter = Adapter()
        adapter.setItemListener(this)
        typeListView = requireView().findViewById(R.id.typesListView)
        typeListView.adapter = adapter
    }

    /**
     * Set first type as default for the first list launching or loading default value
     * @param type first loaded type
     */
    private fun defaultLoad(type: String) {
        if (sharedPreference
                .getString("last_meal_type", null) == null) {
            sharedPreference
                .edit()
                ?.putString("last_meal_type", type)
                ?.apply()
        }
    }

    private fun startMealListFragment () {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.meal_list_container,
                MealListFragment.newInstance(
                    sharedPreference
                    .getString("last_meal_type", null)
                    ?: error("Incorrect type")
                )
            )
            .addToBackStack(MealListFragment.TAG)
            .commit()
    }

    /**
     * Called when item clicked. Loading selected category
     * @param item selected item
     */
    override fun onItemClick(item: TypeModel) {
        adapter.notifyDataSetChanged()
        sharedPreference
            .edit()
            ?.putString("last_meal_type", item.strCategory)
            ?.apply()
        startMealListFragment()
    }

    companion object {
        private const val TYPE_LIST = "TYPE_LIST"
        const val TAG = "TYPE_LIST"

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance() = TypeListFragment().apply {
            //arguments = bundleOf(TYPE_LIST to ...) then get arguments somewhere
        }
    }
}