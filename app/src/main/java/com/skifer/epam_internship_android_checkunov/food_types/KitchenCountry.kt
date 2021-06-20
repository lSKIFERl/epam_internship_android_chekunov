package com.skifer.epam_internship_android_checkunov.food_types

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class KitchenCountry (val country: String) : Parcelable {
    ITALIAN("Итальянская"),
    FRENCH("Французская"),
    GERMAN("Немецкая"),
    MEXICAN("Мексиканская"),
    PAN_ASIAN("Паназиатская"),
    EAST("Восточная"),
    UKRAINIAN("Украинская"),
    RUSSIAN("Русская"),
    USA("Американская"),
    UK("Британская"),
    JAMAICAN("Ямайская")
}
