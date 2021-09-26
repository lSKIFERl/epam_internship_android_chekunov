package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.data.network.MealApi
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toEntity
import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.data.preferences.SortingSharedPreferencesSources
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject


class MealListRepositoryImpl @Inject constructor(
    private val mealApi: MealApi,
    private val prefs: SortingSharedPreferencesSources
): MealListRepository {

    override fun loadDishList(category: String) =
        mealApi.getMealByCategory(category)
            .map { it.listMealModel.map { it.toEntity() } }

    override fun getSort(): Sort = prefs.getSort()

    override fun setSort(sort: Sort): Completable = Completable.fromAction {
        prefs.setSort(sort)
    }
}