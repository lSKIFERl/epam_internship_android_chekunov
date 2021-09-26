package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetLastCategoryUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetMealListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.SetLastCategoryUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetCategoryListUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.SingleLiveEvent
import com.skifer.epam_internship_android_checkunov.presentation.mapper.toUi
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel
import com.skifer.epam_internship_android_checkunov.presentation.model.CategoryModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MealListViewModel (
    private val getMealListUseCase: GetMealListUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val setLastCategory: SetLastCategoryUseCase,
    private val getLastCategory: GetLastCategoryUseCase
    ): ViewModel() {

    private val mutableMealListModel: MutableLiveData<List<MealListItemModel>> = MutableLiveData()

    private val mutableError: SingleLiveEvent<Throwable> = SingleLiveEvent()

    private val mutableCategoryList: MutableLiveData<List<CategoryModel>> = MutableLiveData()

    val mealListModel: LiveData<List<MealListItemModel>>
        get() = mutableMealListModel

    val categoryList: LiveData<List<CategoryModel>>
        get() = mutableCategoryList

    val errorLiveData: LiveData<Throwable>
        get() = mutableError

    private val disposable = CompositeDisposable()

    init {
        loadTypeList()
        loadMealList()
    }

    fun loadMealList() {
        disposable.add(
            getMealListUseCase.invoke(getLastCategory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mutableMealListModel.value = it.map {
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
            getCategoryListUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mutableCategoryList.value = it.map {
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

    fun setCategory(item: String) {
        disposable.add(
            setLastCategory(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({},
                    {
                        mutableError.value = it
                        Log.e(
                            "Net",
                            "Can't put category in prefs",
                            it
                        )
                    }
                )
        )
        loadMealList()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}