package com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.data.network.exception.MealsIsEmptyException
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealModelUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.SingleLiveEvent
import com.skifer.epam_internship_android_checkunov.presentation.mapper.toUi
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MealModelViewModel(
    private val useCase: MealModelUseCase,
    private val id: Int
): ViewModel() {

    private val mutableMeal: MutableLiveData<MealModel> = MutableLiveData()

    private val mutableError: SingleLiveEvent<Throwable> = SingleLiveEvent()

    val meal: LiveData<MealModel>
        get() = mutableMeal

    val error: LiveData<Throwable>
        get() = mutableError

    fun loadData() =
        useCase.invoke(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { if (it != null) {
                    mutableMeal.value = it.toUi()
                } else {
                    val error = MealsIsEmptyException("Loaded MealModel is empty")
                    mutableError.value = error
                    Log.e(
                        "Net",
                        "Error: Can't load meal model",
                        error
                    )
                }
                },
                { e ->
                    mutableError.value = e
                    Log.e(
                        "Net",
                        "Error: Can't load meal model",
                        e
                    )
                }
            )
}