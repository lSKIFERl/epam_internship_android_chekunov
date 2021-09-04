package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.data.network.Network
import com.skifer.epam_internship_android_checkunov.data.network.mapper.fromDBListToEntity
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toDBList
import com.skifer.epam_internship_android_checkunov.domain.entity.TypeModelEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.TypeModelRepository
import io.reactivex.rxjava3.core.Single

class TypeModelRepositoryImpl: TypeModelRepository {

    override fun loadTypeList(): Single<List<TypeModelEntity>> =
            App.instance.database
            .getTypeModelDao()
            .getAll()
            .flatMap {
                if (it.isEmpty()) {
                    Network.dishApiService.getCategory()
                        .map {
                            it.toDBList()
                        }
                        .flatMap {
                            App.instance.database.getTypeModelDao().insert(it)
                            Single.just(it)
                        }
                } else Single.just(it)
            }
            .map {
                it.fromDBListToEntity()
            }
}