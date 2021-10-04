package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.data.network.MealApi
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealListItemEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class MealListRepositoryImpl @Inject constructor(
    private val mealApi: MealApi
) : MealListRepository {

    override fun loadMealList(category: String): Single<List<MealListItemEntity>> =
        mealApi.getMealByCategory(category)
            .map { it.listMealModel.map { modelDto -> modelDto.toEntity() } }
}