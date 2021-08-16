package com.skifer.epam_internship_android_checkunov.data.network.model

import com.google.gson.annotations.SerializedName

data class MealModelListItemDto(
    @SerializedName("idMeal")
    val idMeal: Int,
    @SerializedName("strMeal")
    val strMeal: String?,
    @SerializedName("strMealThumb")
    val strMealThumb: String?
)