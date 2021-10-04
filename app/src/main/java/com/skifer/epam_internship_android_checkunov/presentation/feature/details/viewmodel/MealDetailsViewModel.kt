package com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.data.network.exception.MealsIsEmptyException
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetMealUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.SingleLiveEvent
import com.skifer.epam_internship_android_checkunov.presentation.mapper.toUi
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MealDetailsViewModel(
    private val useCaseGet: GetMealUseCase,
): ViewModel() {

    private val mutableMeal: MutableLiveData<MealModel> = MutableLiveData()

    private val mutableError: SingleLiveEvent<Throwable> = SingleLiveEvent()

    val meal: LiveData<MealModel>
        get() = mutableMeal

    val error: LiveData<Throwable>
        get() = mutableError

    private var disposable: Disposable? = null

    fun loadData(id: Int) {
        disposable = useCaseGet(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { entity ->
                    if (entity != null) {
                        mutableMeal.value = entity.toUi()
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

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}