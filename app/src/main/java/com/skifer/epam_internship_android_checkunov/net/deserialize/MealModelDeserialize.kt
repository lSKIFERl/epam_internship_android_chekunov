package com.skifer.epam_internship_android_checkunov.net.deserialize

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.skifer.epam_internship_android_checkunov.model.Ingredient
import com.skifer.epam_internship_android_checkunov.model.MealModel
import java.lang.reflect.Type

class MealModelDeserialize: JsonDeserializer<List<MealModel>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<MealModel> {
        val jsonItems = json?.asJsonArray
        val meals = mutableListOf<MealModel>()
        jsonItems?.forEach {
            with(it.asJsonObject) {
                val ingredients = mutableListOf<Ingredient>()
                (1..20).forEach { i ->
                    if (!get("strIngredient$i").asString.equals("") &&
                        get("strIngredient$i").asString != null) {
                        ingredients.add(
                            Ingredient(
                                get("strIngredient$i").asString,
                                get("strMeasure$i").asString
                            )
                        )
                    }
                }
                meals.add(
                    MealModel(
                        get("idMeal").asInt,
                        get("strMeal").asString,
                        get("strCategory").asString,
                        get("strTags").asString.split(","),
                        get("strArea").asString,
                        get("strMealThumb").asString,
                        get("strYoutube").asString,
                        ingredients,
                        get("strInstructions").asString
                    )
                )
            }
        }
        return meals
    }
}