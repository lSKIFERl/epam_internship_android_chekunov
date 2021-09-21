package com.skifer.epam_internship_android_checkunov.data.network

import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListMealModel
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListMealModelNet
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListTypeModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DishApi {

    @GET("lookup.php")
    fun getDetailsDish(@Query("i") id: Int): Single<ListMealModel>

    @GET("filter.php")
    fun getDishByCategory(@Query("c") category: String): Single<ListMealModelNet>

    @GET("categories.php")
    fun getCategory(): Single<ListTypeModel>

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}
