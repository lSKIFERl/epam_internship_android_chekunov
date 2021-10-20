package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.domain.entity.Sort
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetSortUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.SetSortUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SettingsViewModel(
    private val setSort: SetSortUseCase,
    private val getSort: GetSortUseCase
) : ViewModel() {

    private val mutableSort = MutableLiveData<Sort>()

    val sortByData: LiveData<Sort>
        get() = mutableSort

    private val disposable = CompositeDisposable()

    init {
        getSortOrder()
    }

    fun setSortOrder(order: Sort) {
        disposable.add(setSort(order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i(TAG, SUCCESS_PUT + order)
                    mutableSort.value = order
                },
                {
                    Log.e(TAG, ERROR_PUT, it)
                }
            )
        )
    }

    private fun getSortOrder() {
        disposable.add(getSort()
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
        )
    }

    fun isSortAsc() = mutableSort.value == Sort.SORT_ASC

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    companion object {
        private const val TAG = "Net"
        private const val ERROR_LOAD = "Can't load category in prefs"
        private const val ERROR_PUT = "Can't put category in prefs"
        private const val SUCCESS_PUT = "Saved in prefs: "
        private const val SUCCESS_LOAD = "Got from prefs: "
    }
}