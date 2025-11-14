package com.tuempresa.bodega.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tuempresa.bodega.data.db.AppDatabase
import com.tuempresa.bodega.data.db.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryDao = AppDatabase.getDatabase(application).categoryDao()

    val categories: Flow<List<CategoryEntity>> = categoryDao.getAll()

    fun insert(name: String) {
        viewModelScope.launch {
            categoryDao.insert(CategoryEntity(name = name))
        }
    }

    fun update(category: CategoryEntity) {
        viewModelScope.launch {
            categoryDao.update(category)
        }
    }

    fun delete(category: CategoryEntity) {
        viewModelScope.launch {
            categoryDao.delete(category)
        }
    }
}
