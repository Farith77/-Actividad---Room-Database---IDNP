package com.tuempresa.bodega.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tuempresa.bodega.data.db.AppDatabase
import com.tuempresa.bodega.data.db.entities.OrderDetailEntity
import com.tuempresa.bodega.data.db.entities.OrderEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val orderDao = db.orderDao()
    private val detailDao = db.orderDetailDao()

    val orders: Flow<List<OrderEntity>> = orderDao.getAll()

    fun getOrdersByCustomer(customerId: Int): Flow<List<OrderEntity>> =
        orderDao.getOrdersByCustomer(customerId)

    fun getOrderDetails(orderId: Int): Flow<List<OrderDetailEntity>> =
        detailDao.getDetailsByOrder(orderId)

    fun createOrder(customerId: Int, details: List<Pair<Int, Int>>) {
        viewModelScope.launch {
            // Insert Order
            val orderId = orderDao.insert(
                OrderEntity(
                    customerID = customerId,
                    orderDate = System.currentTimeMillis()
                )
            )

            // Insert details (productID, quantity)
            details.forEach { (productId, qty) ->
                detailDao.insert(
                    OrderDetailEntity(
                        orderID = orderId.toInt(),
                        productID = productId,
                        quantity = qty
                    )
                )
            }
        }
    }

    fun deleteOrder(order: OrderEntity) {
        viewModelScope.launch { orderDao.delete(order) }
    }
}
