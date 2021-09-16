package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.data.network.DishApi
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealModelEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.MealModelRepository
import io.reactivex.rxjava3.core.Single

class MealModelRepositoryImpl(
    private val dishApi: DishApi
): MealModelRepository {
    override fun loadDishDetails(id: Int): Single<MealModelEntity> =
        dishApi.getDetailsDish(id)
            .map { it.listMealModel.firstOrNull()?.toEntity() }
}