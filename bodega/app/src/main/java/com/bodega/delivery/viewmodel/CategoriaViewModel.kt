package com.bodega.delivery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bodega.delivery.data.database.AppDatabase
import com.bodega.delivery.data.entities.Categoria
import com.bodega.delivery.repository.CategoriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CategoriaRepository

    val todasLasCategorias = repository.todasLasCategorias.asLiveData()

    init {
        val categoriaDao = AppDatabase.getDatabase(application).categoriaDao()
        repository = CategoriaRepository(categoriaDao)
    }

    fun insert(categoria: Categoria) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(categoria)
    }

    fun update(categoria: Categoria) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(categoria)
    }

    fun delete(categoria: Categoria) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(categoria)
    }
}