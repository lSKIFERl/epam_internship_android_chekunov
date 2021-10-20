package com.skifer.epam_internship_android_checkunov.data.database.dao

import androidx.room.*
import com.skifer.epam_internship_android_checkunov.data.database.entities.CategoryModelDB
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CategoryModelDao {

    @Query("SELECT * FROM CategoryModelDB")
    fun getAll(): Single<List<CategoryModelDB>>

    @Query("SELECT * FROM CategoryModelDB WHERE ID_CATEGORY = :id")
    fun getById(id: Long): Single<CategoryModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categories: List<CategoryModelDB>): Completable

    @Update
    fun update(categories: List<CategoryModelDB>)
}