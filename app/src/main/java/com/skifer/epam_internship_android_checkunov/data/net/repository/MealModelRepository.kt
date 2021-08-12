package com.skifer.epam_internship_android_checkunov.net.repository

import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import io.reactivex.rxjava3.core.Single

interface MealModelRepository {
    fun loadDishDetails(id: Int): Single<MealModel>
}

interface MealListRepository {
    fun loadDishList(category: String): Single<List<MealModelListItem>>
}