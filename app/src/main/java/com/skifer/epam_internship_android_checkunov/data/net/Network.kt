package com.skifer.epam_internship_android_checkunov.data.net

import com.google.gson.GsonBuilder
import com.skifer.epam_internship_android_checkunov.model.modellists.ListMealModel
import com.skifer.epam_internship_android_checkunov.model.modellists.ListMealModelNet
import com.skifer.epam_internship_android_checkunov.model.modellists.ListTypeModel
import com.skifer.epam_internship_android_checkunov.data.net.deserializer.MealModelDeserializer
import io.reactivex.rxjava3.core.Single
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(createGsonConverter(type, typeAdapter))
            .build()
            ?: error("Retrofit error")

    private fun getRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            ?: error("Retrofit error")

    val dishDetailsApiService: DishApi
    get() = getRetrofitClient(
        ListMealModel::class.java,
        MealModelDeserializer()
    ).create(DishApi::class.java)

    val dishApiService: DishApi
    get() = getRetrofitClient().create(DishApi::class.java)

}

interface DishApi {
    @GET("lookup.php")
    fun getDetailsDish(@Query("i") id: Int): Single<ListMealModel>

    @GET("filter.php")
    fun getDishByCategory(@Query("c") category: String): Single<ListMealModelNet>

    @GET("categories.php")
    fun getCategory(): Single<ListTypeModel>
}
