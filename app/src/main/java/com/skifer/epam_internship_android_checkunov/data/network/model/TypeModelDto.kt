package com.skifer.epam_internship_android_checkunov.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Model of types
 * @param picture icon of the item
 * @param selected displays whether an item is selected or not
 */
data class TypeModelDto(
        @SerializedName("idCategory")
        val idCategory: Long,
        @SerializedName("strCategory")
        val strCategory: String,
        @SerializedName("strCategoryThumb")
        val strCategoryThumb: String
)