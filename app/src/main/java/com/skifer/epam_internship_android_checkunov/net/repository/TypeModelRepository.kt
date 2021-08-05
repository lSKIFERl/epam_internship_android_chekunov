package com.skifer.epam_internship_android_checkunov.net.repository

import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.net.Network
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

object TypeModelRepository {
    fun createTypeList(): Single<List<TypeModel>> =
        Network.dishApiService.getCategory()
            .map { it.listTypeModel }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}