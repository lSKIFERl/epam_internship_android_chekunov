package com.skifer.epam_internship_android_checkunov.net.repository

import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.net.Network
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

object MealModelRepository {
    fun createDishList(category: String): Single<List<MealModelListItem>> =
        Network.dishApiService.getDishByCategory(category)
            .map { it.listMealModel }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun createDishDetails(id: Int): Single<MealModel?>? =
        Network.dishDetailsApiService.getDetailsDish(id)
            .map { it.listMealModel.firstOrNull() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}