package com.skifer.epam_internship_android_checkunov.domain.repository

import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.domain.entity.MealListItemEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MealListRepository {
    fun loadDishList(category: String): Single<List<MealListItemEntity>>
    fun getSort(): Sort
    fun setSort(sort: Sort): Completable
}