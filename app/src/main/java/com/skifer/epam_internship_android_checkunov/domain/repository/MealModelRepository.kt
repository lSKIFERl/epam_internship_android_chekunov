package com.skifer.epam_internship_android_checkunov.domain.repository

import com.skifer.epam_internship_android_checkunov.domain.entity.MealModelEntity
import io.reactivex.rxjava3.core.Single

interface MealModelRepository {
    fun loadDishDetails(id: Int): Single<MealModelEntity>
}