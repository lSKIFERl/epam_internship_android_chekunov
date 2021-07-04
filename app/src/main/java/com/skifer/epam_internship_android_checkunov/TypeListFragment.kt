package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.net.repository.TypeModelRepository

/**
 * Class of types of food displayed on the screen
 */
class TypeListFragment: Fragment(R.layout.fragment_type_list), Adapter.onItemListener<TypeModel> {

    /**The types list on the screen*/
    private lateinit var typeListView: RecyclerView

    /**The items list that should be on the screen. Contained in [typeListView] */
    private var types: List<TypeModel> = listOf()

    /**[typeListView] custom adapter*/
    private lateinit var adapter: Adapter<TypeModel>

    /**
     * Sets [adapter] properties and binds it with [typeListView]
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadTypes(view)
    }

    private fun loadTypes(view: View) = TypeModelRepository.createTypeList(
            caseComplete = { typesList ->
                if (typesList != null) {
                    this.types = typesList
                    bind(view)
                }
                Log.i("Net", typesList.toString())
            },
            caseError = { t ->
                Log.i("Net", "Can't load types", t)
                Toast.makeText(parentFragment?.context, "Error in loading categories", Toast.LENGTH_LONG).show()
            }
        )

    private fun bind(view: View) {
        adapter = Adapter()
        adapter.setList(types)
        adapter.setItemListener(this)

        typeListView = view.findViewById(R.id.typesListView)
        typeListView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        typeListView.adapter = adapter
    }

    /**
     * Called when item clicked
     * WIP for a while
     */
    override fun onItemClick(item: TypeModel) {
        parentFragmentManager.beginTransaction()
                .replace(
                        R.id.meal_list_container,
                        MealListFragment
                                .newInstance(item.strCategory)
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