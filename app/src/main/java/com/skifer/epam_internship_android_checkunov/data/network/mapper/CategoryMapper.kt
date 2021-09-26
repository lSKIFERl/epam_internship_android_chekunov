package com.skifer.epam_internship_android_checkunov.data.network.mapper

import com.skifer.epam_internship_android_checkunov.data.database.entities.CategoryModelDB
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListCategoryModel
import com.skifer.epam_internship_android_checkunov.domain.entity.CategoryEntity


fun ListCategoryModel.toDBList(): List<CategoryModelDB> {
    val dbModelList: MutableList<CategoryModelDB> = mutableListOf()
    listCategoryModel.forEach {
        dbModelList.add(
            CategoryModelDB(
                it.idCategory,
                it.strCategory,
                it.strCategoryThumb
            )
        )
    }
    return dbModelList.toList()
}

fun List<CategoryModelDB>.fromDBListToEntity(): List<CategoryEntity> {
    val list: MutableList<CategoryEntity> = mutableListOf()
    this.forEach {
        list.add(
            CategoryEntity(
                it.idCategory,
                it.strCategory,
                it.strCategoryThumb
            )
        )
    }
    return list.toList()
}