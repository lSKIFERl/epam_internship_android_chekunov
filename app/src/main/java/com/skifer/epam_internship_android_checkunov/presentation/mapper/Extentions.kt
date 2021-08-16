package com.skifer.epam_internship_android_checkunov.presentation.mapper

import com.skifer.epam_internship_android_checkunov.domain.entity.IngredientEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealModelEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealModelListItemEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.TypeModelEntity
import com.skifer.epam_internship_android_checkunov.presentation.model.Ingredient
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.presentation.model.TypeModel

fun IngredientEntity.toUi() = Ingredient(name, measure)

fun MealModelEntity.toUi() = MealModel(
    idMeal,
    strMeal,
    strCategory,
    tags,
    strArea,
    strMealThumb,
    strYoutube,
    ingredients?.map { it.toUi() },
    strInstructions
)

fun MealModelListItemEntity.toUi() = MealModelListItem(idMeal, strMeal, strMealThumb)

fun TypeModelEntity.toUi() = TypeModel(idCategory, strCategory, strCategoryThumb)