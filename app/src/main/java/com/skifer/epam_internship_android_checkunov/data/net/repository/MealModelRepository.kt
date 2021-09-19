package com.skifer.epam_internship_android_checkunov.data.net.repository

import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.data.net.Network
import com.skifer.epam_internship_android_checkunov.data.net.exception.MealsIsEmptyException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object MealModelRepository {
    fun createDishList(
        category: String,
        caseComplete: (List<MealModelListItem>?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        Network.dishApiService.getDishByCategory(category)
            .map { it.listMealModel }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {caseComplete(it)},
                {caseError(it)}
            )
    }

    fun createDishDetails(
        id: Int,
        caseComplete: (MealModel?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        Network.dishDetailsApiService.getDetailsDish(id)
            .map { it.listMealModel.firstOrNull() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {if (it == null) caseError(MealsIsEmptyException("Dish is empty")) else caseComplete(it)},
                {caseError(it)}
            )
    }
}