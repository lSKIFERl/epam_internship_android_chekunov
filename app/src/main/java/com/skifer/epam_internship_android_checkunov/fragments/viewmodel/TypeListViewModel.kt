package com.skifer.epam_internship_android_checkunov.fragments.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.net.repository.TypeModelRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class TypeListViewModel(private val repository: TypeModelRepository): ViewModel() {
    private val mutableTypeList: MutableLiveData<List<TypeModel>> = MutableLiveData()
    val typeList: LiveData<List<TypeModel>>
        get() = mutableTypeList

    fun loadData() =
        repository
            .loadTypeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mutableTypeList.value = it},
                { e ->
                    Log.e(
                        "Net",
                        "Can't load types",
                        e
                    )
                    throw e
                }
            )
}