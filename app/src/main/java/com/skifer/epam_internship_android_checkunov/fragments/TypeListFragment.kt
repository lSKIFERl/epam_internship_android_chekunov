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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Class of types of food displayed on the screen
 */
class TypeListFragment: Fragment(R.layout.fragment_type_list), Adapter.onItemListener<TypeModel> {

    /**The types list on the screen*/
    private lateinit var typeListView: RecyclerView

    /**[typeListView] custom adapter*/
    private lateinit var adapter: Adapter<TypeModel>

    /**used to unsubscribe from observable*/
    private var disposable: Disposable? = null

    /**
     * Sets [adapter] properties and binds it with [typeListView]
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        disposable = loadTypes()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
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
    private fun loadTypes() =
        TypeModelRepository
            .createTypeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { typesList ->
                    if (typesList != null) {
                        bind(typesList)
                    }
                },
                { e ->
                    Log.e(
                        "Net",
                        "Can't load types",
                        e
                    )
                    Toast.makeText(
                        context,
                        "Error in loading categories",
                        Toast.LENGTH_LONG
                    ).show()
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