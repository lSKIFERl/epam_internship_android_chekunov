package com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.data.network.exception.MealsIsEmptyException
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetMealUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.SingleLiveEvent
import com.skifer.epam_internship_android_checkunov.presentation.mapper.toUi
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MealDetailsViewModel(
    private val getMeal: GetMealUseCase,
): ViewModel() {

    private val mutableMeal: MutableLiveData<MealModel> = MutableLiveData()

    private val mutableError: SingleLiveEvent<String> = SingleLiveEvent()

    val meal: LiveData<MealModel>
        get() = mutableMeal

    val error: LiveData<String>
        get() = mutableError

    private var disposable: Disposable? = null

    fun loadData(id: Int) {
        disposable = getMeal(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { entity ->
                    if (entity != null) {
                        mutableMeal.value = entity.toUi()
                    } else {
                        val error = MealsIsEmptyException(EMPTY_MEAL_LOG)
                        mutableError.value = ERROR_EMPTY_MEAL
                        Log.e(
                            TAG,
                            ERROR,
                            error
                        )
                    }
                },
                { e ->
                    mutableError.value = ERROR
                    Log.e(
                        TAG,
                        ERROR,
                        e
                    )
                }
            )
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    companion object {
        private const val TAG = "Net"
        private var ERROR =
            App.instance.applicationContext.getString(R.string.error_cant_load_meal)
        private var ERROR_EMPTY_MEAL =
            App.instance.applicationContext.getString(R.string.error_empty_meal)
        private var EMPTY_MEAL_LOG = "Loaded MealModel is empty"
    }
}