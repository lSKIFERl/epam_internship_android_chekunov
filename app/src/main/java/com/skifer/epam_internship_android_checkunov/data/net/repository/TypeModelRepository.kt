package com.skifer.epam_internship_android_checkunov.data.net.repository

import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.data.database.entities.TypeModelDB
import com.skifer.epam_internship_android_checkunov.data.net.Network
import com.skifer.epam_internship_android_checkunov.model.TypeModel
import com.skifer.epam_internship_android_checkunov.model.modellists.ListTypeModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

object TypeModelRepository {
    fun createTypeList(
        caseComplete: (List<TypeModel>?) -> Unit,
        caseError: (Throwable) -> Unit
    ) {
        val database = App.instance.getDatabase()
        database
            .getTypeModelDao()
            .getAll()
            .flatMap {
                if (it.isEmpty()) {
                    Network.dishApiService.getCategory()
                        .map {
                            it.toDBList()
                        }
                        .flatMap {
                            database.getTypeModelDao().insert(it)
                            Single.just(it)
                        }
                } else Single.just(it)
            }
            ?.map {
                it.fromDBList()
            }
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {caseComplete(it)},
                {caseError(it)}
            )
    }

    fun ListTypeModel.toDBList(): List<TypeModelDB> {
        val dbModelList: MutableList<TypeModelDB> = mutableListOf()
        listTypeModel.forEach {
            dbModelList.add(
                TypeModelDB(
                    it.idCategory,
                    it.strCategory,
                    it.strCategoryThumb
                )
            )
        }
        return dbModelList.toList()
    }

    fun List<TypeModelDB>.fromDBList(): List<TypeModel> {
        val modelList: MutableList<TypeModel> = mutableListOf()
        this.forEach {
            modelList.add(
                TypeModel(
                    it.idCategory,
                    it.strCategory,
                    it.strCategoryThumb
                )
            )
        }
        return modelList.toList()
    }
}