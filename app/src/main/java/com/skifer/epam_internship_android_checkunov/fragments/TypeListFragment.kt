package com.skifer.epam_internship_android_checkunov.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.net.repository.TypeModelRepository

/**
 * Class of types of food displayed on the screen
 */
class TypeListFragment: Fragment(R.layout.fragment_type_list), Adapter.onItemListener<TypeModel> {

    /**The types list on the screen*/
    private lateinit var typeListView: RecyclerView

    /**[typeListView] custom adapter*/
    private lateinit var adapter: Adapter<TypeModel>

    /**
     * Sets [adapter] properties and binds it with [typeListView]
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadTypes()
    }

    private fun initView() {
        adapter = Adapter()
        adapter.setItemListener(this)

        typeListView = requireView().findViewById(R.id.typesListView)
        typeListView.adapter = adapter
    }

    /**
     * Loading data from network
     */
    private fun loadTypes() = TypeModelRepository.createTypeList(
            caseComplete = { typesList ->
                if (typesList != null) {
                    bind(typesList)
                }
            },
            caseError = { t ->
                Log.i("Net", "Can't load types", t)
                Toast.makeText(parentFragment?.context, "Error in loading categories", Toast.LENGTH_LONG).show()
            }
        )

    /**
     * Binding loaded list from network with recyclerview adapter
     * @param typesList loaded list
     */
    private fun bind(typesList: List<TypeModel>) {
        adapter.setList(typesList)
    }

    /**
     * Called when item clicked. Loading selected category
     * @param item selected item
     */
    override fun onItemClick(item: TypeModel) {
        parentFragmentManager.beginTransaction()
                .replace(
                        R.id.meal_list_container,
                        MealListFragment.newInstance(item.strCategory)
                )
                .commit()
    }

    companion object {
        private const val TYPE_LIST = "TYPE_LIST"

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance() = TypeListFragment().apply {
            //arguments = bundleOf(TYPE_LIST to ...) then get arguments somewhere
        }
    }
}