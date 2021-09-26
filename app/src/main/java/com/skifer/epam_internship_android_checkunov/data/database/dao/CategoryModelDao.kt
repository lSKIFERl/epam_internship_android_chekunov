package com.skifer.epam_internship_android_checkunov.data.database.dao

import androidx.room.*
import com.skifer.epam_internship_android_checkunov.data.database.entities.CategoryModelDB
import io.reactivex.rxjava3.core.Single

@Dao
interface CategoryModelDao {

    @Query("SELECT * FROM CategoryModelDB")
    fun getAll(): Single<List<CategoryModelDB>>

    @Query("SELECT * FROM CategoryModelDB WHERE idCategory = :id")
    fun getById(id: Long): CategoryModelDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categories: List<CategoryModelDB>)

    @Update
    fun update(categories: List<CategoryModelDB>)
}