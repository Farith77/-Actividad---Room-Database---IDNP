package com.tuempresa.bodega.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tuempresa.bodega.data.db.AppDatabase
import com.tuempresa.bodega.data.db.entities.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val productDao = AppDatabase.getDatabase(application).productDao()

    val products: Flow<List<ProductEntity>> = productDao.getAll()

    fun getProductsByCategory(id: Int): Flow<List<ProductEntity>> =
        productDao.getProductsByCategory(id)

    fun insert(name: String, price: Double, categoryId: Int) {
        viewModelScope.launch {
            productDao.insert(
                ProductEntity(
                    productName = name,
                    price = price,
                    categoryID = categoryId
                )
            )
        }
    }

    fun update(product: ProductEntity) {
        viewModelScope.launch { productDao.update(product) }
    }

    fun delete(product: ProductEntity) {
        viewModelScope.launch { productDao.delete(product) }
    }
}
