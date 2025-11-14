package com.tuempresa.bodega.data.db.dao

import androidx.room.*
import com.tuempresa.bodega.data.db.entities.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: OrderEntity): Long

    @Update
    suspend fun update(order: OrderEntity)

    @Delete
    suspend fun delete(order: OrderEntity)

    @Query("SELECT * FROM OrderEntity ORDER BY orderDate DESC")
    fun getAll(): Flow<List<OrderEntity>>

    @Query("SELECT * FROM OrderEntity WHERE orderID = :id")
    suspend fun getById(id: Int): OrderEntity?

    // 🔥 Consulta 1-N (órdenes de un cliente)
    @Query("SELECT * FROM OrderEntity WHERE customerID = :customerId")
    fun getOrdersByCustomer(customerId: Int): Flow<List<OrderEntity>>
}
