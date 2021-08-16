package com.skifer.epam_internship_android_checkunov.data.network.model.modellists

import com.google.gson.annotations.SerializedName
import com.skifer.epam_internship_android_checkunov.data.network.model.MealModelDto

data class ListMealModel (
    @SerializedName(value = "meals")
    val listMealModel: List<MealModelDto>
)