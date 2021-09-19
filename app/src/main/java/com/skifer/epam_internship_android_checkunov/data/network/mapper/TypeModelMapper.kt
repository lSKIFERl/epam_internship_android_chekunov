package com.skifer.epam_internship_android_checkunov.data.network.mapper

import com.skifer.epam_internship_android_checkunov.data.database.entities.TypeModelDB
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListTypeModel
import com.skifer.epam_internship_android_checkunov.domain.entity.TypeModelEntity


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

fun List<TypeModelDB>.fromDBListToEntity(): List<TypeModelEntity> {
    val modelList: MutableList<TypeModelEntity> = mutableListOf()
    this.forEach {
        modelList.add(
            TypeModelEntity(
                it.idCategory,
                it.strCategory,
                it.strCategoryThumb
            )
        )
    }
    return modelList.toList()
}