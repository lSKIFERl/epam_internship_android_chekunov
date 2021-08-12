package com.skifer.epam_internship_android_checkunov.data.net.repository

import com.skifer.epam_internship_android_checkunov.data.net.Network
import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.net.repository.MealModelRepository
import io.reactivex.rxjava3.core.Single

class MealModelRepositoryImpl: MealModelRepository {
    override fun loadDishDetails(id: Int): Single<MealModel> =
        Network.dishDetailsApiService.getDetailsDish(id)
            .map { it.listMealModel.firstOrNull() }
}