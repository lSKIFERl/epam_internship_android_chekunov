package com.skifer.epam_internship_android_checkunov.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealListItemModel(
    val idMeal: Int,
    val strMeal: String?,
    val strMealThumb: String?
) : Parcelable