package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.data.database.database.MealsModelsDataBase
import com.skifer.epam_internship_android_checkunov.data.database.entities.CategoryModelDB
import com.skifer.epam_internship_android_checkunov.data.network.MealApi
import com.skifer.epam_internship_android_checkunov.data.network.mapper.fromDBListToEntity
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toDBList
import com.skifer.epam_internship_android_checkunov.data.preferences.CategorySharedPreferencesSource
import com.skifer.epam_internship_android_checkunov.domain.entity.CategoryEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val mealApi: MealApi,
    private val database: MealsModelsDataBase,
    private val prefs: CategorySharedPreferencesSource
) : CategoryRepository {

    override fun getCategoryList(): Single<List<CategoryEntity>> =
        database
            .getTypeModelDao()
            .getAll()
            .flatMap {
                if (it.isEmpty()) {
                    getDBCategory()
                } else Single.just(it)
            }
            .map {
                it.fromDBListToEntity()
            }

    override fun getLastCategoryId(): Single<Long> =
        Single.just(prefs.getLastCategoryId())

    override fun setLastCategoryId(id: Long): Completable = Completable.fromAction {
        prefs.setLastCategoryId(id)
    }

    private fun getDBCategory(): Single<List<CategoryModelDB>>? =
        mealApi.getCategory()
            .map { listCategoryModel ->
                listCategoryModel.toDBList()
            }
            .flatMap { list ->
                database.getTypeModelDao().insert(list)
                Single.just(list)
            }
}