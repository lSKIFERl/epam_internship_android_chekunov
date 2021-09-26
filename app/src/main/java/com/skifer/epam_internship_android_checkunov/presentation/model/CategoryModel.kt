package com.skifer.epam_internship_android_checkunov.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
        val idCategory: Long,
        val strCategory: String,
        val strCategoryThumb: String
) : Parcelable
