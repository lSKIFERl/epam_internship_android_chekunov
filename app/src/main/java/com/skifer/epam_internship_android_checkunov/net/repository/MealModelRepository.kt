package com.skifer.epam_internship_android_checkunov.net.repository

import com.skifer.epam_internship_android_checkunov.model.MealModel
import com.skifer.epam_internship_android_checkunov.model.MealModelListItem
import com.skifer.epam_internship_android_checkunov.model.modellists.ListMealModel
import com.skifer.epam_internship_android_checkunov.model.modellists.ListMealModelNet
import com.skifer.epam_internship_android_checkunov.net.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MealModelRepository {
    fun createDishList(
        category: String,
        caseComplete: (List<MealModelListItem>?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        Network.dishApiService.getDishByCategory(category).enqueue(
            object: Callback<ListMealModelNet> {

                override fun onResponse(
                    call: Call<ListMealModelNet>,
                    response: Response<ListMealModelNet>
                ) {
                    caseComplete(response.body()?.listMealModel)
                }

                override fun onFailure(call: Call<ListMealModelNet>, t: Throwable) = caseError(t)

            }
        )
    }

    fun createDishDetails(
        id: Int,
        caseComplete: (MealModel?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        Network.dishDetailsApiService.getDetailsDish(id).enqueue(
            object: Callback<ListMealModel> {
                override fun onResponse(
                    call: Call<ListMealModel>,
                    response: Response<ListMealModel>
                ) {
                    caseComplete(response.body()?.listMealModel?.first())
                }

                override fun onFailure(call: Call<ListMealModel>, t: Throwable) = caseError(t)

            }
        )
    }
}