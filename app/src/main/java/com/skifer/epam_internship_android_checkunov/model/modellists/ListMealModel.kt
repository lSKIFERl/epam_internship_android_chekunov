package com.skifer.epam_internship_android_checkunov.model.modellists

import com.google.gson.annotations.SerializedName
import com.skifer.epam_internship_android_checkunov.model.MealModel

data class ListMealModel (
    @SerializedName(value = "meals")
    val listMealModel: List<MealModel>
)