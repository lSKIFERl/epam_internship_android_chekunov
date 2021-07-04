package com.skifer.epam_internship_android_checkunov.net.deserialize

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.skifer.epam_internship_android_checkunov.model.Ingredient
import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.modellists.ListMealModel
import java.lang.reflect.Type

class MealModelDeserialize: JsonDeserializer<ListMealModel> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ListMealModel {
        val jsonItems = json?.asJsonObject?.get("meals")?.asJsonArray
        var meals: ListMealModel? = null
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
                meals = ListMealModel(listOf(
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
                )))
            }
        }
        return meals?: error("Error: Can't bind dish details info")
    }
}