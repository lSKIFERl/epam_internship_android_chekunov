package com.skifer.epam_internship_android_checkunov.data.network

import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListMealModel
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListMealModelNet
import com.skifer.epam_internship_android_checkunov.data.network.model.modellists.ListTypeModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

@Deprecated("Replaced by DI")
object Network {

    private fun getRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            ?: error("Retrofit error")

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
