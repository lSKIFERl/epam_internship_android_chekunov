package com.skifer.epam_internship_android_checkunov.data.network.model.modellists

import com.google.gson.annotations.SerializedName
import com.skifer.epam_internship_android_checkunov.data.network.model.CategoryModelDto

data class ListCategoryModel (
        @SerializedName(value = "categories")
        val listCategoryModel: List<CategoryModelDto>
        )