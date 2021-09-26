package com.skifer.epam_internship_android_checkunov.data.network.mapper

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.skifer.epam_internship_android_checkunov.data.network.model.MealModelDto
import com.skifer.epam_internship_android_checkunov.data.network.model.MealListItemModelDto
import com.skifer.epam_internship_android_checkunov.data.network.model.CategoryModelDto
import com.skifer.epam_internship_android_checkunov.domain.entity.IngredientEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealListItemEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.CategoryEntity

fun MealModelDto.toEntity(): MealEntity {
    val json = JsonParser()
        .parse(Gson().toJson(this))
        .asJsonObject
    val strTags = mutableListOf<String>()
    val ingredients = mutableListOf<IngredientEntity>()
    with(json) {
        (1..20).forEach { i ->
            if (get("strIngredient$i") != null) {
                if (!get("strIngredient$i").asString.equals("") &&
                    get("strIngredient$i").asString != null
                ) {
                    ingredients.add(
                        IngredientEntity(
                            get("strIngredient$i").asString,
                            get("strMeasure$i").asString
                        )
                    )
                }
            }
        }
        if (get("strTags") != null) {
            strTags.addAll(
                get("strTags").asString
                    ?.split(",")
                    ?: listOf<String>()
            )
        }
    }
    return MealEntity(
        idMeal,
        strMeal,
        strCategory,
        strTags,
        strArea,
        strMealThumb,
        strYoutube,
        ingredients,
        strInstructions
    )
}

fun MealListItemModelDto.toEntity() = MealListItemEntity(idMeal, strMeal, strMealThumb)

fun CategoryModelDto.toEntity() = CategoryEntity(idCategory, strCategory, strCategoryThumb)