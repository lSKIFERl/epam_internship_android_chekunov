package com.skifer.epam_internship_android_checkunov.domain.repository

import com.skifer.epam_internship_android_checkunov.domain.entity.Sort
import io.reactivex.rxjava3.core.Completable

interface SortingRepository {
    fun getSort(): Sort
    fun setSort(sort: Sort): Completable
}