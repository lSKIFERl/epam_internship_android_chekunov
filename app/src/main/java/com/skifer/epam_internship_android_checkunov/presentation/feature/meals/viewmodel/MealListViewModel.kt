package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.domain.usecase.*
import com.skifer.epam_internship_android_checkunov.presentation.feature.SingleLiveEvent
import com.skifer.epam_internship_android_checkunov.presentation.mapper.toUi
import com.skifer.epam_internship_android_checkunov.presentation.model.CategoryModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MealListViewModel (
    private val getMealListUseCase: GetMealListUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val setLastCategory: SetLastCategoryUseCase,
    private val getLastCategory: GetLastCategoryUseCase,
    private val setLastCategoryId: SetLastCategoryIdUseCase,
    private val getLastCategoryId: GetLastCategoryIdUseCase
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
    }

    fun loadMealList() {
        disposable.add(
            getMealListUseCase(getCategory())
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
            getCategoryListUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mutableCategoryList.value = it.map {
                            it.toUi()
                        }
                        if (getCategory() == "") {
                            setCategory(it.first().toUi())
                            setCategoryId(it.first().toUi().idCategory)
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
        loadMealList()
    }

    private fun getCategory(): String {
        var lastCategory = ""
        disposable.add(
            getLastCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                      lastCategory = it
                    },
                    {}
                )
            )
        getCategoryId()
        return lastCategory
    }

    fun setCategory(item: CategoryModel) {
        disposable.add(
            setLastCategory(item.strCategory)
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
        loadTypeList()
        loadMealList()
        item.active = true
    }

    private fun setCategoryId(id: Long) {
        disposable.add(
            setLastCategoryId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({},
                    {
                        mutableError.value = it
                        Log.e(
                            "Net",
                            "Can't put category id in prefs",
                            it
                        )
                    }
                )
        )
    }

    private fun getCategoryId() {
        disposable.add(
            getLastCategoryId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                    },
                    {
                    }
                )
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}