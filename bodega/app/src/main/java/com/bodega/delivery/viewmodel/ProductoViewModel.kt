package com.bodega.delivery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bodega.delivery.data.database.AppDatabase
import com.bodega.delivery.data.entities.Producto
import com.bodega.delivery.repository.ProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ProductoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductoRepository

    private val searchQuery = MutableStateFlow("")
    private val categoryFilter = MutableStateFlow<Int?>(null) // null significa sin filtro

    // Usamos combine y flatMapLatest para reaccionar a cambios en búsqueda y filtro
    val productos = searchQuery.combine(categoryFilter) { query, categoryId ->
        Pair(query, categoryId)
    }.flatMapLatest { (query, categoryId) ->
        when {
            categoryId != null -> repository.getProductosPorCategoria(categoryId)
            query.isNotEmpty() -> repository.buscarProductos(query)
            else -> repository.todosLosProductos
        }
    }.asLiveData()

    init {
        val productoDao = AppDatabase.getDatabase(application).productoDao()
        repository = ProductoRepository(productoDao)
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun setCategoryFilter(categoryId: Int?) {
        categoryFilter.value = categoryId
    }

    fun insert(producto: Producto) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(producto)
    }

    fun update(producto: Producto) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(producto)
    }

    fun delete(producto: Producto) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(producto)
    }
}