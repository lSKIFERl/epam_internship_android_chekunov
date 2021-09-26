package com.skifer.epam_internship_android_checkunov.data.network.model.modellists

import com.google.gson.annotations.SerializedName
import com.skifer.epam_internship_android_checkunov.data.network.model.MealListItemModelDto

data class ListOfMealModelList (
        @SerializedName(value = "meals")
        val listMealModel: List<MealListItemModelDto>
        )