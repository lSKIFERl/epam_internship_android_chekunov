package com.skifer.epam_internship_android_checkunov.data.net.repository

import com.skifer.epam_internship_android_checkunov.data.net.Network
import com.skifer.epam_internship_android_checkunov.net.repository.MealListRepository

class MealListRepositoryImpl: MealListRepository {
    override fun loadDishList(category: String) =
        Network.dishApiService.getDishByCategory(category)
            .map { it.listMealModel }
}