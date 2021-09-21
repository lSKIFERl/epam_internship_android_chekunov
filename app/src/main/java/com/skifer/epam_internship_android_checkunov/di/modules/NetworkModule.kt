package com.skifer.epam_internship_android_checkunov.di.modules

import com.skifer.epam_internship_android_checkunov.data.network.DishApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideDishApi(): DishApi =
        Retrofit.Builder()
            .baseUrl(DishApi.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DishApi::class.java)


}
