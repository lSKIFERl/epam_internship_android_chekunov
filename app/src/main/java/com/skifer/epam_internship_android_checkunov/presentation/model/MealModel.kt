package com.skifer.epam_internship_android_checkunov.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Dish model
 * @param idMeal dish identifier
 * @param strMeal name of the dish
 * @param strCategory category of the dish like "Chicken", "Beef", etc
 * @param tags tags of the dish like "Meat", "Casserole", etc
 * @param strArea where it from
 * @param strMealThumb link to image of the dish
 * @param strYoutube link to video guide on YouTube
 * @param ingredients all what we need to cook
 * @param strInstructions cook guide
 */
@Parcelize
data class MealModel(
        val idMeal: Int,
        val strMeal: String?,
        val strCategory: String?,
        val tags: List<String>?,
        val strArea: String?,
        val strMealThumb: String?,
        val strYoutube: String?,
        val ingredients: List<Ingredient>?,
        val strInstructions: String?
) : Parcelable