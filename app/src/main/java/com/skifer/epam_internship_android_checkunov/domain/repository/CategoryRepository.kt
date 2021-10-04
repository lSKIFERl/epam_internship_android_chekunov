package com.skifer.epam_internship_android_checkunov.domain.repository

import com.skifer.epam_internship_android_checkunov.domain.entity.CategoryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CategoryRepository {
    fun loadCategoryList(): Single<List<CategoryEntity>>
    fun setLastCategoryId(id: Long): Completable
    fun getLastCategoryId(): Single<Long>
}