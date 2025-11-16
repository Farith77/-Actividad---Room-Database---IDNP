package com.bodega.delivery.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.bodega.delivery.data.entities.Pedido
import com.bodega.delivery.data.relations.PedidoConDetalles
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pedido: Pedido): Long // Devuelve el ID del pedido insertado

    @Update
    suspend fun update(pedido: Pedido)

    @Delete
    suspend fun delete(pedido: Pedido)

    @Query("SELECT * FROM pedidos ORDER BY fecha DESC")
    fun getAllPedidos(): Flow<List<Pedido>>

    @Transaction
    @Query("SELECT * FROM pedidos WHERE id = :pedidoId")
    fun getPedidoConDetalles(pedidoId: Int): Flow<PedidoConDetalles>

    @Query("SELECT * FROM pedidos WHERE clienteId = :clienteId ORDER BY fecha DESC")
    fun getPedidosByCliente(clienteId: Int): Flow<List<Pedido>>

    @Query("SELECT * FROM pedidos WHERE estado = :estado ORDER BY fecha DESC")
    fun getPedidosByEstado(estado: String): Flow<List<Pedido>>
}