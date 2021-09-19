package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.data.network.DishApi
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository

class MealListRepositoryImpl(
    private val dishApi: DishApi
): MealListRepository {
    override fun loadDishList(category: String) =
        dishApi.getDishByCategory(category)
            .map { it.listMealModel.map { it.toEntity() } }
}