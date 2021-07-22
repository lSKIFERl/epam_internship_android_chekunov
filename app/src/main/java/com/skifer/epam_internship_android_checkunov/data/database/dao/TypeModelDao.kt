package com.skifer.epam_internship_android_checkunov.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.skifer.epam_internship_android_checkunov.data.database.entities.TypeModelDB

@Dao
interface TypeModelDao {
    @Query("SELECT * FROM TypeModelDB")
    fun getAll(): List<TypeModelDB?>?

    @Query("SELECT * FROM TypeModelDB WHERE idCategory = :id")
    fun getById(id: Long): TypeModelDB?

    @Insert
    fun insert(employee: TypeModelDB?)

    @Update
    fun update(employee: TypeModelDB?)
}