package com.skifer.epam_internship_android_checkunov.data.network

import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListMealModel
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListOfMealModelList
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListCategoryModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("lookup.php")
    fun getDetailsMeal(@Query("i") id: Int): Single<ListMealModel>

    @GET("filter.php")
    fun getMealByCategory(@Query("c") category: String): Single<ListOfMealModelList>

    @GET("categories.php")
    fun getCategory(): Single<ListCategoryModel>

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}
