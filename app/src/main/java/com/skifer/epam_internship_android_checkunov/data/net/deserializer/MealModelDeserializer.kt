package com.skifer.epam_internship_android_checkunov.data.net.deserializer

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.skifer.epam_internship_android_checkunov.model.Ingredient
import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.modellists.ListMealModel
import java.lang.reflect.Type

class MealModelDeserializer: JsonDeserializer<ListMealModel> {
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
                    if (!get("strIngredient$i").isJsonNull) {
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
                }
                var strTags = listOf<String>()
                if (!get("strTags").isJsonNull) {
                    strTags = get("strTags").asString?.split(",")?: listOf<String>()
                }
                Log.i("Net", strTags.toString())

                meals = ListMealModel(listOf(
                        Gson().fromJson(this, MealModel::class.java).copy(
                                ingredients = ingredients,
                                tags = strTags)
                ))
            }
        }
        return meals?: error("Error: Can't bind dish details info")
    }
}