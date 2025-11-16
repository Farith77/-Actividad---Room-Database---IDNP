package com.bodega.delivery.repository

import com.bodega.delivery.data.dao.ClienteDao
import com.bodega.delivery.data.entities.Cliente
import kotlinx.coroutines.flow.Flow

class ClienteRepository(private val clienteDao: ClienteDao) {

    val todosLosClientes: Flow<List<Cliente>> = clienteDao.getAllClientes()

    fun buscarClientes(query: String): Flow<List<Cliente>> {
        return clienteDao.searchClientes(query)
    }

    fun getClienteConPedidos(clienteId: Int) = clienteDao.getClienteConPedidos(clienteId)

    suspend fun insert(cliente: Cliente) {
        clienteDao.insert(cliente)
    }

    suspend fun update(cliente: Cliente) {
        clienteDao.update(cliente)
    }

    suspend fun delete(cliente: Cliente) {
        clienteDao.delete(cliente)
    }
}