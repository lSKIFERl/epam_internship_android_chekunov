package com.skifer.epam_internship_android_checkunov.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Dish model
 * @param idMeal dish identificator
 * @param strMeal name of the dish
 * @param strCategory category of the dish like "Chicken", "Beef", etc
 * @param strTags tags of the dish like "Meat", "Casserole", etc
 * @param strArea where it from
 * @param strMealThumb link to image of the dish
 * @param strYoutube link to video guide on YouTube
 * @param ingredients all what we need to cook
 * @param strInstructions cook guide
 */
@Parcelize
data class MealModel(
        @SerializedName("idMeal")
        val idMeal: Int,
        @SerializedName("strMeal")
        val strMeal: String?,
        @SerializedName("strCategory")
        val strCategory: String?,
        @SerializedName("strTags")
        val strTags: List<String>?,
        @SerializedName("strArea")
        val strArea: String?,
        @SerializedName("strMealThumb")
        val strMealThumb: String,
        @SerializedName("strYoutube")
        val strYoutube: String?,
        val ingredients: List<Ingredient>?,
        @SerializedName("strInstructions")
        val strInstructions: String?
) : Parcelable

/**
 * Ingredient model
 * @param name name of the ingredient
 * @param measure how much we need in spoons, cups, etc.
 */
@Parcelize
data class Ingredient(
        val name: String,
        val measure: String?
) : Parcelable