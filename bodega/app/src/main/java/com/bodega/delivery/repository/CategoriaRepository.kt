package com.bodega.delivery.repository

import com.bodega.delivery.data.dao.CategoriaDao
import com.bodega.delivery.data.entities.Categoria
import kotlinx.coroutines.flow.Flow

class CategoriaRepository(private val categoriaDao: CategoriaDao) {

    val todasLasCategorias: Flow<List<Categoria>> = categoriaDao.getAllCategorias()

    suspend fun insert(categoria: Categoria) {
        categoriaDao.insert(categoria)
    }

    suspend fun update(categoria: Categoria) {
        categoriaDao.update(categoria)
    }

    suspend fun delete(categoria: Categoria) {
        categoriaDao.delete(categoria)
    }
}