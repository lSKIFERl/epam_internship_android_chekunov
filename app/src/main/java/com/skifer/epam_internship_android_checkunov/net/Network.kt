package com.skifer.epam_internship_android_checkunov.net

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.net.deserialize.MealModelDeserialize
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.Type


object Network {


    private fun createGsonConverter(type: Type, typeAdapter: Any): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(type, typeAdapter)
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }

    private fun getRetrofitClient(type: Type, typeAdapter: Any): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1")
            .addConverterFactory(createGsonConverter(type, typeAdapter))
            .build()
            ?: error("Retrofit error")

    private fun getRetrofitClient(): Retrofit =
        Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            ?: error("Retrofit error")

    val dishDetailsApiService: DishApi
    get() = getRetrofitClient(
        object : TypeToken<List<MealModel>>() {}.type,
        MealModelDeserialize()
    ).create(DishApi::class.java)

    val dishApiService: DishApi
    get() = getRetrofitClient().create(DishApi::class.java)

}

interface DishApi {
    @GET("lookup.php")
    fun getDetailsDish(@Query("i") id: Int): Call<List<MealModel>>

    @GET("filter.php")
    fun getDishByCategory(@Query("c") category: String): Call<List<MealModelListItem>>

    @GET("categories.php")
    fun getCategory(): Call<List<TypeModel>>
}
