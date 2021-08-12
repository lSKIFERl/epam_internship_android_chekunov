package com.skifer.epam_internship_android_checkunov.fragments.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.data.net.exception.MealsIsEmptyException
import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.net.repository.MealModelRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MealModelViewModel(
    private val repository: MealModelRepository,
    private val id: Int
): ViewModel() {
    private val mutableMeal: MutableLiveData<MealModel> = MutableLiveData()
    val meal: LiveData<MealModel>
        get() = mutableMeal

    fun loadData() =
        repository
            .loadDishDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { if (it != null) {
                    mutableMeal.value = it
                } else {
                    Log.e(
                        "Net",
                        "Error: Can't load meal model",
                        MealsIsEmptyException("Loaded MealModel is empty")
                    )
                }
                },
                { e ->
                    Log.e(
                        "Net",
                        "Error: Can't load meal model",
                        e
                    )
                    throw e
                }
            )
}