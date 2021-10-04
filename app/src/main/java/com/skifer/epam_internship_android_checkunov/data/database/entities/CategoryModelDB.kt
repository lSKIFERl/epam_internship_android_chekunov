package com.skifer.epam_internship_android_checkunov.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryModelDB(
    @PrimaryKey
    @ColumnInfo(name = "ID_CATEGORY")
    val idCategory: Long,
    @ColumnInfo(name = "STR_CATEGORY_NAME")
    val strCategory: String,
    @ColumnInfo(name = "STR_CATEGORY_THUMB")
    val strCategoryThumb: String
)
