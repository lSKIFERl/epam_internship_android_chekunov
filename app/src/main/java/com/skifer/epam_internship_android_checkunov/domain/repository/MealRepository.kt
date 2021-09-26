package com.skifer.epam_internship_android_checkunov.domain.repository

import com.skifer.epam_internship_android_checkunov.domain.entity.MealEntity
import io.reactivex.rxjava3.core.Single

interface MealRepository {
    fun loadDishDetails(id: Int): Single<MealEntity>
}