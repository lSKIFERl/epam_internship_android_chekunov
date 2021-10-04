package com.skifer.epam_internship_android_checkunov.domain.repository

import com.skifer.epam_internship_android_checkunov.domain.entity.MealListItemEntity
import io.reactivex.rxjava3.core.Single

interface MealListRepository {
    fun loadMealList(category: String): Single<List<MealListItemEntity>>
}