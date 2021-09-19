package com.skifer.epam_internship_android_checkunov.data.network.model.modellists

import com.google.gson.annotations.SerializedName
import com.skifer.epam_internship_android_checkunov.data.network.model.TypeModelDto

data class ListTypeModel (
        @SerializedName(value = "categories")
        val listTypeModel: List<TypeModelDto>
        )