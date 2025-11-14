package com.tuempresa.bodega.data.db.dao

import androidx.room.*
import com.tuempresa.bodega.data.db.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Update
    suspend fun update(product: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)

    @Query("SELECT * FROM Product ORDER BY productName")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM Product WHERE productID = :id")
    suspend fun getById(id: Int): ProductEntity?

    // 🔥 Consulta 1-N (productos de una categoría)
    @Query("SELECT * FROM Product WHERE categoryID = :categoryId")
    fun getProductsByCategory(categoryId: Int): Flow<List<ProductEntity>>
}
