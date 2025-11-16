package com.bodega.delivery.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Transaction
import com.bodega.delivery.data.entities.Cliente
import com.bodega.delivery.data.relations.ClienteConPedidos
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: Cliente)

    @Update
    suspend fun update(cliente: Cliente)

    @Delete
    suspend fun delete(cliente: Cliente)

    @Query("SELECT * FROM clientes ORDER BY apellido, nombre ASC")
    fun getAllClientes(): Flow<List<Cliente>>

    @Query("SELECT * FROM clientes WHERE id = :clienteId")
    fun getClienteById(clienteId: Int): Flow<Cliente>

    @Query("SELECT * FROM clientes WHERE nombre LIKE :query || '%' OR apellido LIKE :query || '%'")
    fun searchClientes(query: String): Flow<List<Cliente>>

    @Transaction
    @Query("SELECT * FROM clientes WHERE id = :clienteId")
    fun getClienteConPedidos(clienteId: Int): Flow<ClienteConPedidos>
}