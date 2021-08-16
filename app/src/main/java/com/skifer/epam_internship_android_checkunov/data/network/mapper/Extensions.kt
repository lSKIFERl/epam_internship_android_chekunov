package com.skifer.epam_internship_android_checkunov.data.network.mapper

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.skifer.epam_internship_android_checkunov.data.network.model.MealModelDto
import com.skifer.epam_internship_android_checkunov.data.network.model.MealModelListItemDto
import com.skifer.epam_internship_android_checkunov.data.network.model.TypeModelDto
import com.skifer.epam_internship_android_checkunov.domain.entity.IngredientEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealModelEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealModelListItemEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.TypeModelEntity

fun MealModelDto.toEntity(): MealModelEntity {
    val json = JsonParser()
        .parse(Gson().toJson(this))
        .asJsonObject
    val strTags = mutableListOf<String>()
    val ingredients = mutableListOf<IngredientEntity>()
    with(json) {
        (1..20).forEach { i ->
            if (!get("strIngredient$i").isJsonNull) {
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
        if (!get("strTags").isJsonNull) {
            strTags.addAll(
                get("strTags").asString
                    ?.split(",")
                    ?: listOf<String>()
            )
        }
    }
    return MealModelEntity(
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

fun MealModelListItemDto.toEntity() = MealModelListItemEntity(idMeal, strMeal, strMealThumb)

fun TypeModelDto.toEntity() = TypeModelEntity(idCategory, strCategory, strCategoryThumb)