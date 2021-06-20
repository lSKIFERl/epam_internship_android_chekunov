package com.skifer.epam_internship_android_checkunov.food_types

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class FoodType(val title: String?) : Parcelable {

    SOUP("Суп"),
    MEAT("Мясо"),
    FISH("Рыба"),
    MILK("Молочное"),
    BAKERY("Выпечка"),
    DESSERTS("Десерты");

}
