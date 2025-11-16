package com.bodega.delivery.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.bodega.delivery.data.entities.Producto
import com.bodega.delivery.data.relations.ProductoConCategorias
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: Producto)

    @Update
    suspend fun update(producto: Producto)

    @Delete
    suspend fun delete(producto: Producto)

    @Transaction
    @Query("SELECT * FROM productos")
    fun getAllProductosConCategoria(): Flow<List<ProductoConCategorias>>

    @Transaction
    @Query("SELECT * FROM productos WHERE id = :productoId")
    fun getProductoConCategoriaById(productoId: Int): Flow<ProductoConCategorias>

    @Transaction
    @Query("SELECT * FROM productos WHERE nombre LIKE :query || '%'")
    fun searchProductos(query: String): Flow<List<ProductoConCategorias>>

    @Transaction
    @Query("SELECT * FROM productos WHERE categoriaId = :categoriaId")
    fun getProductosByCategoria(categoriaId: Int): Flow<List<ProductoConCategorias>>
}