package com.tuempresa.bodega.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tuempresa.bodega.data.db.AppDatabase
import com.tuempresa.bodega.data.db.entities.CustomerEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application) : AndroidViewModel(application) {

    private val customerDao = AppDatabase.getDatabase(application).customerDao()

    val customers: Flow<List<CustomerEntity>> = customerDao.getAll()

    fun insert(first: String, last: String, email: String) {
        viewModelScope.launch {
            customerDao.insert(
                CustomerEntity(
                    firstName = first,
                    lastName = last,
                    email = email
                )
            )
        }
    }

    fun update(customer: CustomerEntity) {
        viewModelScope.launch { customerDao.update(customer) }
    }

    fun delete(customer: CustomerEntity) {
        viewModelScope.launch { customerDao.delete(customer) }
    }
}
