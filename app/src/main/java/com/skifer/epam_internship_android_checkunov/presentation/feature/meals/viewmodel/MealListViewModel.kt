package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.TypeListUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.SingleLiveEvent
import com.skifer.epam_internship_android_checkunov.presentation.mapper.toUi
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.presentation.model.TypeModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MealListViewModel(
    private val mealListUseCase: MealListUseCase,
    private val typeListUseCase: TypeListUseCase
    ): ViewModel() {

    private val mutableMealList: MutableLiveData<List<MealModelListItem>> = MutableLiveData()

    private val mutableError: SingleLiveEvent<Throwable> = SingleLiveEvent()

    private val mutableTypeList: MutableLiveData<List<TypeModel>> = MutableLiveData()

    val mealList: LiveData<List<MealModelListItem>>
        get() = mutableMealList

    val typeList: LiveData<List<TypeModel>>
        get() = mutableTypeList

    val errorLiveData: LiveData<Throwable>
        get() = mutableError

    private val disposable = CompositeDisposable()

    fun loadMealList(category: String) {
        disposable.add(
            mealListUseCase.invoke(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mutableMealList.value = it.map {
                            it.toUi()
                        }
                    },
                    { e ->
                        mutableError.value = e
                        Log.e(
                            "Net",
                            "Error: can't load dish list",
                            e
                        )
                    }
                )
        )
    }

    fun loadTypeList() {
        disposable.add(
            typeListUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mutableTypeList.value = it.map {
                            it.toUi()
                        }
                    },
                    { e ->
                        mutableError.value = e
                        Log.e(
                            "Net",
                            "Can't load types",
                            e
                        )
                    }
                )
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}