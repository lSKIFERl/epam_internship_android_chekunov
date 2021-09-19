package com.skifer.epam_internship_android_checkunov.presentation.feature.types.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.domain.usecase.TypeListUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.SingleLiveEvent
import com.skifer.epam_internship_android_checkunov.presentation.mapper.toUi
import com.skifer.epam_internship_android_checkunov.presentation.model.TypeModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class TypeListViewModel(private val useCase: TypeListUseCase): ViewModel() {

    private val mutableTypeList: MutableLiveData<List<TypeModel>> = MutableLiveData()

    private val mutableError: SingleLiveEvent<Throwable> = SingleLiveEvent()

    val typeList: LiveData<List<TypeModel>>
        get() = mutableTypeList

    val errorLiveData: LiveData<Throwable>
        get() = mutableError

    fun loadData() =
        useCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableTypeList.value = it.map {
                        it.toUi() }
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
}