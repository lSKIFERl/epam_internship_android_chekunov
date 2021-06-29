package com.skifer.epam_internship_android_checkunov.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

/**
 * Model of types
 * @param picture icon of the item
 * @param selected displays whether an item is selected or not
 */
@Parcelize
data class TypeModel(
        @DrawableRes val picture: Int,
        var selected: Boolean
) : Parcelable
