package com.skifer.epam_internship_android_checkunov.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Ingredient model
 * @param name name of the ingredient
 * @param measure how much we need in spoons, cups, etc.
 */
@Parcelize
data class IngredientModel(
    val name: String,
    val measure: String?
) : Parcelable