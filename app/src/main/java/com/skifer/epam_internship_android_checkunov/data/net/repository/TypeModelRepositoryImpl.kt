package com.skifer.epam_internship_android_checkunov.data.net.repository

import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.data.database.entities.TypeModelDB
import com.skifer.epam_internship_android_checkunov.data.net.Network
import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.model.modellists.ListTypeModel
import com.skifer.epam_internship_android_checkunov.net.repository.TypeModelRepository
import io.reactivex.rxjava3.core.Single

class TypeModelRepositoryImpl: TypeModelRepository {
    override fun loadTypeList(): Single<List<TypeModel>> =
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
                it.fromDBList()
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

    fun List<TypeModelDB>.fromDBList(): List<TypeModel> {
        val modelList: MutableList<TypeModel> = mutableListOf()
        this.forEach {
            modelList.add(
                TypeModel(
                    it.idCategory,
                    it.strCategory,
                    it.strCategoryThumb
                )
            )
        }
        return modelList.toList()
    }
}