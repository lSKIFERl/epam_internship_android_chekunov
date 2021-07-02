package com.skifer.epam_internship_android_checkunov.net.repository

import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.net.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealModelRepository {
    fun createDishList(
        category: String,
        caseComplete: (List<MealModelListItem>?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        Network.dishApiService.getDishByCategory(category).enqueue(
            object: Callback<List<MealModelListItem>> {

                override fun onResponse(
                    call: Call<List<MealModelListItem>>,
                    response: Response<List<MealModelListItem>>
                ) {
                    caseComplete(response.body())
                }

                override fun onFailure(call: Call<List<MealModelListItem>>, t: Throwable) = caseError(t)

            }
        )
    }

    fun createDishDetails(
        id: Int,
        caseComplete: (MealModel?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        Network.dishDetailsApiService.getDetailsDish(id).enqueue(
            object: Callback<List<MealModel>> {
                override fun onResponse(
                    call: Call<List<MealModel>>,
                    response: Response<List<MealModel>>
                ) {
                    caseComplete(response.body()?.first())
                }

                override fun onFailure(call: Call<List<MealModel>>, t: Throwable) = caseError(t)

            }
        )
    }
}