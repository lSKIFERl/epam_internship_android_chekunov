package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.data.network.MealApi
import com.skifer.epam_internship_android_checkunov.data.network.exception.MealsIsEmptyException
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.MealRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi
): MealRepository {

    override fun loadMealDetails(id: Int): Single<MealEntity> =
        mealApi.getDetailsMeal(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
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