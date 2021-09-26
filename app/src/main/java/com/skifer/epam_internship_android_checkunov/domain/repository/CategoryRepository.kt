package com.skifer.epam_internship_android_checkunov.domain.repository

import com.skifer.epam_internship_android_checkunov.domain.entity.CategoryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface CategoryRepository {
    fun loadCategoryList(): Single<List<CategoryEntity>>
    fun setLastCategory(categoryName: String): Completable
    fun getLastCategory(): Observable<String>
}