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

class SettingsViewModel (
    private val setSort: SetSortUseCase,
    private val getSort: GetSortUseCase
): ViewModel() {

    private val mutableSort = MutableLiveData<Sort>()

    val sortBy: LiveData<Sort>
        get() = mutableSort

    private var lastOrder: Sort = Sort.SORT_ASC

    private val disposable = CompositeDisposable()

    init {
        getSortOrder()
    }

    private fun setSortOrder(order: Sort) {
        lastOrder = order
        disposable.add(setSort(order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i("Prefs", "Saved $order in prefs")
                },
                {
                    Log.e("Prefs", "Can't put $order in prefs", it)
                }
            )
        )
    }

    private fun getSortOrder() {
        /*disposable.add(
            getSort()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.i("Prefs", "Got $it from prefs")
                        lastOrder = it
                    },
                    {
                        Log.e("Prefs", "Can't load prefs", it)
                        lastOrder = Sort.SORT_ASC
                    }
                )
        )*/
        lastOrder = getSort()
    }

    fun isSortAsc() = lastOrder == Sort.SORT_ASC

    fun setAsc() = setSortOrder(Sort.SORT_ASC)

    fun setDesc() = setSortOrder(Sort.SORT_DESC)

    fun apply() {
        mutableSort.value = lastOrder
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}