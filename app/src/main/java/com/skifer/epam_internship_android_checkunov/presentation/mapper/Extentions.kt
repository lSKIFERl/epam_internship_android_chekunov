package com.skifer.epam_internship_android_checkunov.presentation.mapper

import com.skifer.epam_internship_android_checkunov.domain.entity.CategoryEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.IngredientEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealEntity
import com.skifer.epam_internship_android_checkunov.domain.entity.MealListItemEntity
import com.skifer.epam_internship_android_checkunov.presentation.model.CategoryModel
import com.skifer.epam_internship_android_checkunov.presentation.model.IngredientModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModel

fun IngredientEntity.toUi() = IngredientModel(name, measure)

fun MealEntity.toUi() = MealModel(
    idMeal,
    strMeal,
    strCategory,
    tags,
    strArea,
    strMealThumb,
    strYoutube?.let { it.substring(it.lastIndexOf("=") + 1) },
    ingredients?.map { it.toUi() },
    strInstructions
)

fun MealListItemEntity.toUi() = MealListItemModel(idMeal, strMeal, strMealThumb)

fun CategoryEntity.toUi() = CategoryModel(idCategory, strCategory, strCategoryThumb, false)