package com.skifer.epam_internship_android_checkunov.net.repository

import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.net.Network
import io.reactivex.rxjava3.core.Single

object TypeModelRepository {
    fun createTypeList(): Single<List<TypeModel>> =
        Network.dishApiService.getCategory()
            .map { it.listTypeModel }
}