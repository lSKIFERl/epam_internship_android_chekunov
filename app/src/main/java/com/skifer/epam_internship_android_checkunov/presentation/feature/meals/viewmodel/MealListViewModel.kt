package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealListUseCase
import com.skifer.epam_internship_android_checkunov.presentation.mapper.toUi
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModelListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MealListViewModel (
    private val useCase: MealListUseCase,
    private val category: String) : ViewModel() {
    private val mutableMealList: MutableLiveData<List<MealModelListItem>> = MutableLiveData()
    val mealList: LiveData<List<MealModelListItem>>
        get() = mutableMealList

    fun loadData() =
        useCase.invoke(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableMealList.value = it.map {
                        it.toUi()
                    }
                },
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