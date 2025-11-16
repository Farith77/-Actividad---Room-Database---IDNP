package com.bodega.delivery.repository

import com.bodega.delivery.data.dao.ProductoDao
import com.bodega.delivery.data.entities.Producto
import kotlinx.coroutines.flow.Flow

class ProductoRepository(private val productoDao: ProductoDao) {

    val todosLosProductos = productoDao.getAllProductosConCategoria()

    fun buscarProductos(query: String) = productoDao.searchProductos(query)

    fun getProductosPorCategoria(categoriaId: Int) = productoDao.getProductosByCategoria(categoriaId)

    suspend fun insert(producto: Producto) {
        productoDao.insert(producto)
    }

    suspend fun update(producto: Producto) {
        productoDao.update(producto)
    }

    suspend fun delete(producto: Producto) {
        productoDao.delete(producto)
    }
}