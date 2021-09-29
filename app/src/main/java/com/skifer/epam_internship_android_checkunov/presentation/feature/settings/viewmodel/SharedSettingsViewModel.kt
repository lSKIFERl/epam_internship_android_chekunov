package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetSortUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.SetSortUseCase
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SharedSettingsViewModel (
    private val setSort: SetSortUseCase,
    private val getSort: GetSortUseCase
): ViewModel() {

    val sortBy = MutableLiveData<Sort>()

    var lastOrder: Sort = Sort.SORT_ASC

    private val disposable = CompositeDisposable()

    init {
        getSortOrder()
    }

    fun sort(listModel: List<MealListItemModel>?) =
        if (getSort() == Sort.SORT_DESC) {
            listModel?.sortedByDescending { it.strMeal }
        } else {
            listModel?.sortedBy { it.strMeal }
        }

    fun setSortOrder(order: Sort) {
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

    fun getSortOrder() {
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


    fun apply() {
        sortBy.value = getSort()
    }

    override fun onCleared() {
        sortBy.value = lastOrder
        disposable.dispose()
        super.onCleared()
    }
}