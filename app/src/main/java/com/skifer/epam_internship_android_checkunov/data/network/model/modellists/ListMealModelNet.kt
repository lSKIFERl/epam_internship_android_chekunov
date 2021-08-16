package com.skifer.epam_internship_android_checkunov.data.network.model.modellists

import com.google.gson.annotations.SerializedName
import com.skifer.epam_internship_android_checkunov.data.network.model.MealModelListItemDto

data class ListMealModelNet (
        @SerializedName(value = "meals")
        val listMealModel: List<MealModelListItemDto>
        )