package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.data.database.entities.TypeModelDB
import com.skifer.epam_internship_android_checkunov.data.network.Network
import com.skifer.epam_internship_android_checkunov.data.network.mapper.toEntity
import com.skifer.epam_internship_android_checkunov.data.network.model.TypeModelDto
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListTypeModel
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
                it.fromDBList().map { it.toEntity() }
            }

    fun ListTypeModel.toDBList(): List<TypeModelDB> {
        val dbModelList: MutableList<TypeModelDB> = mutableListOf()
        listTypeModel.forEach {
            dbModelList.add(
                TypeModelDB(
                    it.idCategory,
                    it.strCategory,
                    it.strCategoryThumb
                )
            )
        }
        return dbModelList.toList()
    }

    fun List<TypeModelDB>.fromDBList(): List<TypeModelDto> {
        val modelList: MutableList<TypeModelDto> = mutableListOf()
        this.forEach {
            modelList.add(
                TypeModelDto(
                    it.idCategory,
                    it.strCategory,
                    it.strCategoryThumb
                )
            )
        }
        return modelList.toList()
    }
}