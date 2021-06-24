package com.skifer.epam_internship_android_checkunov.food_types

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Country of the dish
 */
@Parcelize
enum class Cuisine : Parcelable {
    ITALIAN,
    FRENCH,
    GERMAN,
    MEXICAN,
    PAN_ASIAN,
    EAST,
    UKRAINIAN,
    RUSSIAN,
    USA,
    UK,
    JAMAICAN
}
