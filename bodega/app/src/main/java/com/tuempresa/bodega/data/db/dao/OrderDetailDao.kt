package com.tuempresa.bodega.data.db.dao

import androidx.room.*
import com.tuempresa.bodega.data.db.entities.OrderDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderDetail: OrderDetailEntity)

    @Update
    suspend fun update(orderDetail: OrderDetailEntity)

    @Delete
    suspend fun delete(orderDetail: OrderDetailEntity)

    @Query("""
        SELECT * FROM OrderDetail 
        WHERE orderID = :orderId
    """)
    fun getDetailsByOrder(orderId: Int): Flow<List<OrderDetailEntity>>

    @Query("""
        SELECT * FROM OrderDetail 
        WHERE productID = :productId
    """)
    fun getOrdersByProduct(productId: Int): Flow<List<OrderDetailEntity>>
}
