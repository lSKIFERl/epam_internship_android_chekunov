package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.data.network.Network
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository

class MealListRepositoryImpl: MealListRepository {
    override fun loadDishList(category: String) =
        Network.dishApiService.getDishByCategory(category)
            .map { it.listMealModel.map { it.toEntity() } }
}