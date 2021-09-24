package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.data.network.DishApi
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toEntity
import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.data.preferences.SortingSharedPreferencesSources
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealListRepositoryImpl @Inject constructor(
    private val dishApi: DishApi,
    private val prefs: SortingSharedPreferencesSources
): MealListRepository {

    override fun loadDishList(category: String) =
        dishApi.getDishByCategory(category)
            .map { it.listMealModel.map { it.toEntity() } }

    override fun getSort(): Sort = prefs.getSort()

    override fun setSort(sort: Sort): Completable = Completable.fromAction {
        prefs.setSort(sort)
    }
}