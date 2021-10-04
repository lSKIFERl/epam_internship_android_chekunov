package com.skifer.epam_internship_android_checkunov.data.network.mapper

import com.skifer.epam_internship_android_checkunov.data.database.entities.CategoryModelDB
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListCategoryModel
import com.skifer.epam_internship_android_checkunov.domain.entity.CategoryEntity


fun ListCategoryModel.toDBList(): List<CategoryModelDB> =
    listCategoryModel.map {
        CategoryModelDB(
            it.idCategory,
            it.strCategory,
            it.strCategoryThumb
        )
    }

fun List<CategoryModelDB>.fromDBListToEntity(): List<CategoryEntity> =
    map {
        CategoryEntity(
            it.idCategory,
            it.strCategory,
            it.strCategoryThumb
        )
    }
