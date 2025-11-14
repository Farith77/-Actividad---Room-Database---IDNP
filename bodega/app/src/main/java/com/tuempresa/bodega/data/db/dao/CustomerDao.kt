package com.tuempresa.bodega.data.db.dao

import androidx.room.*
import com.tuempresa.bodega.data.db.entities.CustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: CustomerEntity)

    @Update
    suspend fun update(customer: CustomerEntity)

    @Delete
    suspend fun delete(customer: CustomerEntity)

    @Query("SELECT * FROM Customer ORDER BY firstName")
    fun getAll(): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM Customer WHERE customerID = :id")
    suspend fun getById(id: Int): CustomerEntity?
}
