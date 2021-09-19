package com.skifer.epam_internship_android_checkunov.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Dish model
 * @param idMeal dish identificator
 * @param strMeal name of the dish
 * @param strCategory category of the dish like "Chicken", "Beef", etc
 * @param strArea where it from
 * @param strMealThumb link to image of the dish
 * @param strYoutube link to video guide on YouTube
 * @param strInstructions cook guide
 */
data class MealModelDto(
        @SerializedName("idMeal")
        val idMeal: Int,
        @SerializedName("strMeal")
        val strMeal: String?,
        @SerializedName("strCategory")
        val strCategory: String?,
        @SerializedName("strArea")
        val strArea: String?,
        @SerializedName("strMealThumb")
        val strMealThumb: String?,
        @SerializedName("strYoutube")
        val strYoutube: String?,
        @SerializedName("strInstructions")
        val strInstructions: String?,
        @SerializedName(value = "strTags")
        val strTags: String?,
        @SerializedName(value = "strIngredient1")
        val strIngredient1: String?,
        @SerializedName(value = "strIngredient2")
        val strIngredient2: String?,
        @SerializedName(value = "strIngredient3")
        val strIngredient3: String?,
        @SerializedName(value = "strIngredient4")
        val strIngredient4: String?,
        @SerializedName(value = "strIngredient5")
        val strIngredient5: String?,
        @SerializedName(value = "strIngredient6")
        val strIngredient6: String?,
        @SerializedName(value = "strIngredient7")
        val strIngredient7: String?,
        @SerializedName(value = "strIngredient8")
        val strIngredient8: String?,
        @SerializedName(value = "strIngredient9")
        val strIngredient9: String?,
        @SerializedName(value = "strIngredient10")
        val strIngredient10: String?,
        @SerializedName(value = "strIngredient11")
        val strIngredient11: String?,
        @SerializedName(value = "strIngredient12")
        val strIngredient12: String?,
        @SerializedName(value = "strIngredient13")
        val strIngredient13: String?,
        @SerializedName(value = "strIngredient14")
        val strIngredient14: String?,
        @SerializedName(value = "strIngredient15")
        val strIngredient15: String?,
        @SerializedName(value = "strIngredient16")
        val strIngredient16: String?,
        @SerializedName(value = "strIngredient17")
        val strIngredient17: String?,
        @SerializedName(value = "strIngredient18")
        val strIngredient18: String?,
        @SerializedName(value = "strIngredient19")
        val strIngredient19: String?,
        @SerializedName(value = "strIngredient20")
        val strIngredient20: String?,
        @SerializedName(value = "strMeasure1")
        val strMeasure1: String?,
        @SerializedName(value = "strMeasure2")
        val strMeasure2: String?,
        @SerializedName(value = "strMeasure3")
        val strMeasure3: String?,
        @SerializedName(value = "strMeasure4")
        val strMeasure4: String?,
        @SerializedName(value = "strMeasure5")
        val strMeasure5: String?,
        @SerializedName(value = "strMeasure6")
        val strMeasure6: String?,
        @SerializedName(value = "strMeasure7")
        val strMeasure7: String?,
        @SerializedName(value = "strMeasure8")
        val strMeasure8: String?,
        @SerializedName(value = "strMeasure9")
        val strMeasure9: String?,
        @SerializedName(value = "strMeasure10")
        val strMeasure10: String?,
        @SerializedName(value = "strMeasure11")
        val strMeasure11: String?,
        @SerializedName(value = "strMeasure12")
        val strMeasure12: String?,
        @SerializedName(value = "strMeasure13")
        val strMeasure13: String?,
        @SerializedName(value = "strMeasure14")
        val strMeasure14: String?,
        @SerializedName(value = "strMeasure15")
        val strMeasure15: String?,
        @SerializedName(value = "strMeasure16")
        val strMeasure16: String?,
        @SerializedName(value = "strMeasure17")
        val strMeasure17: String?,
        @SerializedName(value = "strMeasure18")
        val strMeasure18: String?,
        @SerializedName(value = "strMeasure19")
        val strMeasure19: String?,
        @SerializedName(value = "strMeasure20")
        val strMeasure20: String?
)