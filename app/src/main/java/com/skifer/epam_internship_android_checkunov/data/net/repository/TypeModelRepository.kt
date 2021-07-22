package com.skifer.epam_internship_android_checkunov.data.net.repository

import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.data.net.Network
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object TypeModelRepository {
    fun createTypeList(
        caseComplete: (List<TypeModel>?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        Network.dishApiService.getCategory()
            .map { it.listTypeModel }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { caseComplete(it) },
                { caseError(it) }
            )
    }
}