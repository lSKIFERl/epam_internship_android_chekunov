package com.skifer.epam_internship_android_checkunov.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.skifer.epam_internship_android_checkunov.food_types.FoodType
import com.skifer.epam_internship_android_checkunov.food_types.KitchenCountry

class MealModel(
        val id: Int,
        val name: String?,
        val type: FoodType?,
        val country: KitchenCountry?,
        @DrawableRes val picture: Int
)