package com.skifer.epam_internship_android_checkunov.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TypeModelDB(
    @PrimaryKey
    @ColumnInfo(name = "idCategory")
    val idCategory: Long,
    @ColumnInfo(name = "strCategory")
    val strCategory: String,
    @ColumnInfo(name = "strCategoryThumb")
    val strCategoryThumb: String
)
