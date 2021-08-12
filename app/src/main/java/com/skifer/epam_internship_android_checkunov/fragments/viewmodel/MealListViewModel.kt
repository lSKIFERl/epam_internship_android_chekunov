package com.skifer.epam_internship_android_checkunov.fragments.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.net.repository.MealListRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MealListViewModel (
    private val repository: MealListRepository,
    private val category: String) : ViewModel() {
    private val mutableMealList: MutableLiveData<List<MealModelListItem>> = MutableLiveData()
    val mealList: LiveData<List<MealModelListItem>>
        get() = mutableMealList

    fun loadData() =
        repository
            .loadDishList(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mutableMealList.value = it},
                { e ->
                    Log.e(
                        "Net",
                        "Error: can't load dish list",
                        e
                    )
                    throw e
                }
            )
}