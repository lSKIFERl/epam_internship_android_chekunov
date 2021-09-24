package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.data.database.database.ModelsDataBase
import com.skifer.epam_internship_android_checkunov.data.network.DishApi
import com.skifer.epam_internship_android_checkunov.data.network.mapper.fromDBListToEntity
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toDBList
import com.skifer.epam_internship_android_checkunov.data.preferences.CategorySharedPreferencesSource
import com.skifer.epam_internship_android_checkunov.domain.entity.TypeModelEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.TypeModelRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TypeModelRepositoryImpl @Inject constructor(
    private val dishApi: DishApi,
    private val database: ModelsDataBase,
    private val prefs: CategorySharedPreferencesSource
): TypeModelRepository {
  
    override fun loadTypeList(): Single<List<TypeModelEntity>> =
            database
            .getTypeModelDao()
            .getAll()
            .flatMap {
                if (it.isEmpty()) {
                    dishApi.getCategory()
                        .map {
                            it.toDBList()
                        }
                        .flatMap {
                            database.getTypeModelDao().insert(it)
                            Single.just(it)
                        }
                } else Single.just(it)
            }
            .map {
                it.fromDBListToEntity()
            }

    override fun getLastType() = prefs.getLastCategoryName()

    override fun setLastType(categoryName: String): Completable = Completable.fromAction {
        prefs.setLastCategoryName(categoryName)
    }
}