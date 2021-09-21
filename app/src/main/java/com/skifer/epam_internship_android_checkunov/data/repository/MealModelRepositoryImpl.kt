package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.data.network.DishApi
import com.skifer.epam_internship_android_checkunov.data.network.exception.MealsIsEmptyException
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealModelEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.MealModelRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealModelRepositoryImpl @Inject constructor(
    private val dishApi: DishApi
): MealModelRepository {

    override fun loadDishDetails(id: Int): Single<MealModelEntity> =
        dishApi.getDetailsDish(id)
            .flatMap {
                it.listMealModel.firstOrNull()?.toEntity()?.let {
                    Single.just(it)
                }?: Single.error(
                    MealsIsEmptyException(
                        App.instance.getString(R.string.error_empty_meal)
                    )
                )
            }
}