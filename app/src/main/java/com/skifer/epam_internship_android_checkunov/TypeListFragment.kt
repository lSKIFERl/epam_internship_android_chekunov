package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.TypeModel

/**
 * Class of types of food displayed on the screen
 */
class TypeListFragment: Fragment(R.layout.fragment_type_list), Adapter.onItemListener<TypeModel> {

    /**The types list on the screen*/
    private lateinit var typeListView: RecyclerView

    /**The items list that should be on the screen. Contained in [typeListView] */
    private var types: List<TypeModel> = someTypes()

    /**[typeListView] custom adapter*/
    private lateinit var adapter: Adapter<TypeModel>

    /**
     * Sets [adapter] properties and binds it with [typeListView]
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = Adapter()
        adapter.setList(types)
        adapter.setItemListener(this)

        typeListView = view.findViewById(R.id.typesListView)
        typeListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        typeListView.adapter = adapter
    }

    /**
     * Called when item clicked
     * WIP for a while
     */
    override fun onItemClick(item: TypeModel) {
        //TODO: do smth logic in the future
    }

    /**
     * Builds a list of types. May be removed in the future
     */
    private fun someTypes() = listOf(
            TypeModel(
                    picture = R.drawable.type_meat,
                    selected = true
            ),
            TypeModel(
                    picture = R.drawable.type_cakes,
                    selected = false
            ),
            TypeModel(
                    picture = R.drawable.type_pasta,
                    selected = false
            ),
            TypeModel(
                    picture = R.drawable.type_burito,
                    selected = false
            ),
            TypeModel(
                    picture = R.drawable.type_another_meat,
                    selected = false
            )
    )

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