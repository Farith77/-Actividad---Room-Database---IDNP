package com.bodega.delivery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.bodega.delivery.data.database.AppDatabase
import com.bodega.delivery.data.entities.DetallePedido
import com.bodega.delivery.data.entities.Pedido
import com.bodega.delivery.data.relations.PedidoConDetalles
import com.bodega.delivery.repository.PedidoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PedidoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PedidoRepository

    private val _filter = MutableLiveData<Pair<String, Any?>>()

    // switchMap reacciona a los cambios en el filtro
    val pedidos: LiveData<List<Pedido>> = _filter.switchMap { filter ->
        when (filter.first) {
            "BY_CLIENTE" -> repository.getPedidosPorCliente(filter.second as Int).asLiveData()
            "BY_ESTADO" -> repository.getPedidosPorEstado(filter.second as String).asLiveData()
            else -> repository.todosLosPedidos.asLiveData() // ALL
        }
    }

    init {
        val pedidoDao = AppDatabase.getDatabase(application).pedidoDao()
        val detallePedidoDao = AppDatabase.getDatabase(application).detallePedidoDao()
        repository = PedidoRepository(pedidoDao, detallePedidoDao)
        // Establecer el filtro inicial para cargar todos los pedidos
        _filter.postValue(Pair("ALL", null))
    }

    fun setFilter(filterType: String, value: Any?) {
        _filter.value = Pair(filterType, value)
    }

    fun getPedidoConDetalles(pedidoId: Int): LiveData<PedidoConDetalles> {
        return repository.getPedidoConDetalles(pedidoId).asLiveData()
    }

    fun insertPedidoCompleto(pedido: Pedido, detalles: List<DetallePedido>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPedidoCompleto(pedido, detalles)
    }

    fun update(pedido: Pedido) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(pedido)
    }

    fun delete(pedido: Pedido) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(pedido)
    }
}