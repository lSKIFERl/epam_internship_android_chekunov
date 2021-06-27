package com.skifer.epam_internship_android_checkunov.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.skifer.epam_internship_android_checkunov.food_types.Cuisine
import com.skifer.epam_internship_android_checkunov.food_types.FoodType
import kotlinx.parcelize.Parcelize

/**
 * Dish model
 * @param id dish identificator (will be used in DB)
 * @param title name of the dish
 * @param type type of the dish
 * @param country where it from
 * @param picture image of the dish
 */
@Parcelize
data class MealModel(
        val id: Int,
        val title: String?,
        val type: List<FoodType>,
        val country: Cuisine?,
        val ingredients: List<Ingredient>,
        @DrawableRes val picture: Int
) : Parcelable

/**
 * Ingredient model
 * @param id ingredient identificator (will be used in DB)
 * @param name name of the ingredient
 * @param count how much ingredient we need to cook
 * @param measure spoons, cups, etc.
 */
@Parcelize
data class Ingredient(
        val id: Int,
        val name: String,
        val count: Int,
        val measure: String = ""
) : Parcelable