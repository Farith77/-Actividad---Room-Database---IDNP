package com.bodega.delivery.data.database

import android.content.Context
import android.util.Log
import com.bodega.delivery.R
import com.bodega.delivery.data.entities.Categoria
import com.bodega.delivery.data.entities.Cliente
import com.bodega.delivery.data.entities.Producto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader

object DatabaseSeeder {

    private const val TAG = "DatabaseSeeder"

    fun seed(database: AppDatabase, context: Context) {
        try {
            seedClientes(database, context)
            seedCategorias(database, context)
            seedProductos(database, context)
            // Nota: Los pedidos y detalles no se cargan inicialmente, se crean desde la app.
            Log.i(TAG, "Base de datos poblada exitosamente.")
        } catch (e: Exception) {
            Log.e(TAG, "Error al poblar la base de datos", e)
        }
    }

    private fun seedClientes(database: AppDatabase, context: Context) {
        val jsonString = readJsonFromRaw(context, R.raw.clientes_inicial)
        val type = object : TypeToken<List<Cliente>>() {}.type
        val clientes: List<Cliente> = Gson().fromJson(jsonString, type)
        runBlocking {
            database.clienteDao().insert(*clientes.toTypedArray()) // Use insert vararg
        }
    }

    private fun seedCategorias(database: AppDatabase, context: Context) {
        val jsonString = readJsonFromRaw(context, R.raw.categorias_inicial)
        val type = object : TypeToken<List<Categoria>>() {}.type
        val categorias: List<Categoria> = Gson().fromJson(jsonString, type)
        runBlocking {
            database.categoriaDao().insert(*categorias.toTypedArray()) // Use insert vararg
        }
    }

    private fun seedProductos(database: AppDatabase, context: Context) {
        val jsonString = readJsonFromRaw(context, R.raw.productos_inicial)
        val type = object : TypeToken<List<Producto>>() {}.type
        val productos: List<Producto> = Gson().fromJson(jsonString, type)
        runBlocking {
            database.productoDao().insert(*productos.toTypedArray()) // Use insert vararg
        }
    }

    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use(BufferedReader::readText)
    }
}

// Extension functions to allow vararg inserts in DAOs if they don't exist
// This is a common pattern to avoid creating a separate insertAll method for seeding.
suspend fun ClienteDao.insert(vararg clientes: Cliente) {
    for (cliente in clientes) {
        insert(cliente)
    }
}
suspend fun CategoriaDao.insert(vararg categorias: Categoria) {
    for (categoria in categorias) {
        insert(categoria)
    }
}
suspend fun ProductoDao.insert(vararg productos: Producto) {
    for (producto in productos) {
        insert(producto)
    }
}