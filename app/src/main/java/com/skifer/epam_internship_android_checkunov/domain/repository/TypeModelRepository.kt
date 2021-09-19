package com.skifer.epam_internship_android_checkunov.domain.repository

import com.skifer.epam_internship_android_checkunov.domain.entity.TypeModelEntity
import io.reactivex.rxjava3.core.Single

interface TypeModelRepository {
    fun loadTypeList(): Single<List<TypeModelEntity>>
}