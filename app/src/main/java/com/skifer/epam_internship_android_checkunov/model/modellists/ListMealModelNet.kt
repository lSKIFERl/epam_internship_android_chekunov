package com.skifer.epam_internship_android_checkunov.model.modellists

import com.google.gson.annotations.SerializedName
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem

data class ListMealModelNet (
        @SerializedName(value = "meals")
        val listMealModel: List<MealModelListItem>
        )