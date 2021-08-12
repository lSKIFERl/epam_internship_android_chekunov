package com.skifer.epam_internship_android_checkunov.net.repository

import com.skifer.epam_internship_android_checkunov.model.TypeModel
import io.reactivex.rxjava3.core.Single

interface TypeModelRepository {
    fun loadTypeList(): Single<List<TypeModel>>
}