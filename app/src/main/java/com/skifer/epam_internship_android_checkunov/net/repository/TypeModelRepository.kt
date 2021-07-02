package com.skifer.epam_internship_android_checkunov.net.repository

import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.net.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeModelRepository {
    fun createTypeList(
        caseComplete: (List<TypeModel>?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        Network.dishApiService.getCategory().enqueue(
            object: Callback<List<TypeModel>> {
                override fun onResponse(
                    call: Call<List<TypeModel>>,
                    response: Response<List<TypeModel>>
                ) {
                    caseComplete(response.body())
                }

                override fun onFailure(call: Call<List<TypeModel>>, t: Throwable) = caseError(t)

            }
        )
    }
}