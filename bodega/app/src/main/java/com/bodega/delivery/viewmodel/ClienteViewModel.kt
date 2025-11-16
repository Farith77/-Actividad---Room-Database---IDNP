package com.bodega.delivery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bodega.delivery.data.database.AppDatabase
import com.bodega.delivery.data.entities.Cliente
import com.bodega.delivery.repository.ClienteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ClienteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ClienteRepository

    // Flow para la búsqueda. Vacío por defecto para obtener todos los clientes.
    private val searchQuery = MutableStateFlow("")

    // Usamos flatMapLatest para reaccionar a los cambios en searchQuery
    val clientes = searchQuery.flatMapLatest { query ->
        if (query.isEmpty()) {
            repository.todosLosClientes
        } else {
            repository.buscarClientes(query)
        }
    }.asLiveData()

    init {
        val clienteDao = AppDatabase.getDatabase(application).clienteDao()
        repository = ClienteRepository(clienteDao)
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun getClienteConPedidos(clienteId: Int) = repository.getClienteConPedidos(clienteId).asLiveData()

    fun insert(cliente: Cliente) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(cliente)
    }

    fun update(cliente: Cliente) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(cliente)
    }

    fun delete(cliente: Cliente) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(cliente)
    }
}