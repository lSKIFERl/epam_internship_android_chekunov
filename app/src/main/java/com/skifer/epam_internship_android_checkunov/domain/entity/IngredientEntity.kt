package com.skifer.epam_internship_android_checkunov.domain.entity

/**
 * Ingredient model
 * @param name name of the ingredient
 * @param measure how much we need in spoons, cups, etc.
 */

data class IngredientEntity(
    val name: String,
    val measure: String?
)