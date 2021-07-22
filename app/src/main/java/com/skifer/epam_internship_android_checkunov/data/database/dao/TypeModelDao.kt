package com.skifer.epam_internship_android_checkunov.data.database.dao

import androidx.room.*
import com.skifer.epam_internship_android_checkunov.data.database.entities.TypeModelDB
import io.reactivex.rxjava3.core.Single

@Dao
interface TypeModelDao {
    @Query("SELECT * FROM TypeModelDB")
    fun getAll(): Single<List<TypeModelDB>>

    @Query("SELECT * FROM TypeModelDB WHERE idCategory = :id")
    fun getById(id: Long): TypeModelDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(type: TypeModelDB)

    @Update
    fun update(types: List<TypeModelDB>)
}