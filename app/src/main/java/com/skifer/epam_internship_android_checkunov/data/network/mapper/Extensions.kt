package com.skifer.epam_internship_android_checkunov.data.network.mapper

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.skifer.epam_internship_android_checkunov.data.network.model.MealListItemModelDto
import com.skifer.epam_internship_android_checkunov.data.network.model.MealModelDto
import com.skifer.epam_internship_android_checkunov.domain.entity.IngredientEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealListItemEntity

private const val FIRST_ID = 1
private const val LAST_ID = 20

private const val INGREDIENT = "strIngredient"
private const val MEASURE = "strMeasure"
private const val TAG = "strTags"
private const val DELIMITER = ','

fun MealModelDto.toEntity(): MealEntity {
    val json = JsonParser()
        .parse(Gson().toJson(this))
        .asJsonObject
    val strTags = mutableListOf<String>()
    val ingredients = mutableListOf<IngredientEntity>()
    with(json) {
        (FIRST_ID..LAST_ID).forEach { i ->
            if (get(INGREDIENT + i) != null) {
                if (!get(INGREDIENT + i).asString.equals("") &&
                    get(INGREDIENT + i).asString != null
                ) {
                    ingredients.add(
                        IngredientEntity(
                            get(INGREDIENT + i).asString,
                            get(MEASURE + i).asString
                        )
                    )
                }
            }
        }
        if (get(TAG) != null) {
            strTags.addAll(
                get(TAG).asString
                    ?.split(DELIMITER)
                    ?: listOf()
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