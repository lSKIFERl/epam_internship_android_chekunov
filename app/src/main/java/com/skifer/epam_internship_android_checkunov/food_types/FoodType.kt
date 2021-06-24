package com.skifer.epam_internship_android_checkunov.food_types

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Type of the dish
 */
@Parcelize
enum class FoodType : Parcelable {

    SOUP,
    MEAT,
    FISH,
    MILK,
    BAKERY,
    DESSERTS;

}
