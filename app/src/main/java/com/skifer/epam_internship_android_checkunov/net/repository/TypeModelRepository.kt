package com.skifer.epam_internship_android_checkunov.net.repository

import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.model.modellists.ListTypeModel
import com.skifer.epam_internship_android_checkunov.net.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TypeModelRepository {
    fun createTypeList(
        caseComplete: (List<TypeModel>?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        Network.dishApiService.getCategory().enqueue(
            object: Callback<ListTypeModel> {
                override fun onResponse(
                    call: Call<ListTypeModel>,
                    response: Response<ListTypeModel>
                ) = caseComplete(response.body()?.listTypeModel)

                override fun onFailure(call: Call<ListTypeModel>, t: Throwable) = caseError(t)

            }
        )
    }
}