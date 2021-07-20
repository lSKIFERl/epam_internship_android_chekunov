package com.skifer.epam_internship_android_checkunov.model.modellists

import com.google.gson.annotations.SerializedName
import com.skifer.epam_internship_android_checkunov.model.TypeModel

data class ListTypeModel (
        @SerializedName(value = "categories")
        val listTypeModel: List<TypeModel>
        )