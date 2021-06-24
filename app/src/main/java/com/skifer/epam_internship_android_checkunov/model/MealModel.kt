package com.skifer.epam_internship_android_checkunov.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.skifer.epam_internship_android_checkunov.food_types.Cuisine
import com.skifer.epam_internship_android_checkunov.food_types.FoodType
import kotlinx.parcelize.Parcelize

/**
 * Dish model
 * @param id dish identificator (will used in DB)
 * @param title name of the dish
 * @param type type of the dish
 * @param country where it from
 * @param picture image of the dish
 */
@Parcelize
data class MealModel(
        val id: Int,
        val title: String?,
        val type: FoodType?,
        val country: Cuisine?,
        @DrawableRes val picture: Int
) : Parcelable