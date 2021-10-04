package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.domain.entity.Sort
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetSortUseCase
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SharedSettingsViewModel(
    private val getSort: GetSortUseCase
) : ViewModel() {

    private val mutableSort = MutableLiveData<Sort>()

    val sortBy: LiveData<Sort>
        get() = mutableSort

    private var disposable: Disposable? = null

    private var lastOrder = Sort.SORT_ASC

    init {
        getOrder()
    }

    private fun getOrder() {
        disposable = getSort()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i(TAG, SUCCESS_LOAD + it)
                    mutableSort.value = it
                },
                {
                    Log.e(TAG, ERROR_LOAD, it)
                    mutableSort.value = Sort.SORT_ASC
                }
            )
    }

    fun sort(listModel: List<MealListItemModel>?) =
        if (mutableSort.value == Sort.SORT_DESC) {
            listModel?.sortedByDescending { it.strMeal }
        } else {
            listModel?.sortedBy { it.strMeal }
        }

    fun setSort(sort: Sort) {
        lastOrder = sort
    }

    fun apply() {
        mutableSort.value = lastOrder
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    companion object {
        private const val TAG = "Net"
        private const val ERROR_LOAD = "Can't load category in prefs"
        private const val SUCCESS_LOAD = "Got from prefs: "
    }
}