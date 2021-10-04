package com.skifer.epam_internship_android_checkunov.domain.repository

import com.skifer.epam_internship_android_checkunov.domain.entity.Sort
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface SortingRepository {
    fun getSort(): Single<Sort>
    fun setSort(sort: Sort): Completable
}