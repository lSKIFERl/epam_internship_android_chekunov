package com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.data.network.exception.MealsIsEmptyException
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealModelUseCase
import com.skifer.epam_internship_android_checkunov.presentation.mapper.toUi
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MealModelViewModel(
    private val useCase: MealModelUseCase,
    private val id: Int
): ViewModel() {
    private val mutableMeal: MutableLiveData<MealModel> = MutableLiveData()
    val meal: LiveData<MealModel>
        get() = mutableMeal

    fun loadData() =
        useCase.invoke(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { if (it != null) {
                    mutableMeal.value = it.toUi()
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