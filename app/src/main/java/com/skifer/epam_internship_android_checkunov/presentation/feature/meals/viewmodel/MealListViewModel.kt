package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetCategoryListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetLastCategoryIdUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetMealListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.SetLastCategoryIdUseCase
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
        getCategory()
    }

    private fun loadMealList(lastCategory: String) {
        disposable.add(
            getMealListUseCase(lastCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mutableMealListModel.value = it.map { entity ->
                            entity.toUi()
                        }
                    },
                    { e ->
                        mutableError.value = e
                        Log.e(
                            TAG,
                            ERROR_MEAL_LIST,
                            e
                        )
                    }
                )
        )
    }

    private fun loadCategoryList(idCategory: Int) {
        disposable.add(
            getCategoryListUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        val categoryList = it.map { entity ->
                            entity.toUi()
                        }
                        selectActive(categoryList, idCategory)
                        mutableCategoryList.value = categoryList
                    },
                    { e ->
                        mutableError.value = e
                        Log.e(
                            TAG,
                            ERROR_CATEGORY_LIST,
                            e
                        )
                    }
                )
        )
    }

    private fun getCategory() {
        disposable.add(
            getLastCategoryId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        loadCategoryList(it.toInt())
                    },
                    {
                        loadCategoryList(-1)
                    }
                )
            )
    }

    fun setCategory(item: CategoryModel) {
        disposable.add(
            setLastCategoryId(item.idCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        loadCategoryList(item.idCategory.toInt())
                    },
                    {
                        mutableError.value = it
                        Log.e(
                            TAG,
                            ERROR_PREFS,
                            it
                        )
                    }
                )
        )
    }

    private fun selectActive(categoryList: List<CategoryModel>, idCategory: Int) {
        if (idCategory == -1) {
            categoryList.first().active = true
            setCategory(categoryList.first())
        } else {
            categoryList.forEach {
                if (it.idCategory.toInt() == idCategory){
                    it.active = true
                    loadMealList(it.strCategory)
                }
            }
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    companion object {
        private const val TAG = "Net"
        private const val ERROR_PREFS = "Can't put category in prefs"
        private var ERROR_MEAL_LIST =
            App.instance.applicationContext.getString(R.string.error_cant_load_meal_list)
        private var ERROR_CATEGORY_LIST =
            App.instance.applicationContext.getString(R.string.error_cant_load_categories)
    }
}