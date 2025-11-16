package com.bodega.delivery.repository

import androidx.annotation.WorkerThread
import com.bodega.delivery.data.dao.DetallePedidoDao
import com.bodega.delivery.data.dao.PedidoDao
import com.bodega.delivery.data.entities.DetallePedido
import com.bodega.delivery.data.entities.Pedido
import com.bodega.delivery.data.entities.ProductoPedidoCrossRef

class PedidoRepository(private val pedidoDao: PedidoDao, private val detallePedidoDao: DetallePedidoDao) {

    val todosLosPedidos = pedidoDao.getAllPedidos()

    fun getPedidosPorCliente(clienteId: Int) = pedidoDao.getPedidosByCliente(clienteId)

    fun getPedidosPorEstado(estado: String) = pedidoDao.getPedidosByEstado(estado)

    fun getPedidoConDetalles(pedidoId: Int) = pedidoDao.getPedidoConDetalles(pedidoId)

    @WorkerThread
    suspend fun insertPedidoCompleto(pedido: Pedido, detalles: List<DetallePedido>) {
        // Insertar el pedido principal y obtener su ID
        val pedidoId = pedidoDao.insert(pedido)

        // Asignar el ID del pedido a cada detalle y a la tabla de referencia cruzada
        val detallesConId = detalles.map { it.copy(pedidoId = pedidoId.toInt()) }
        val crossRefs = detalles.map {
            ProductoPedidoCrossRef(
                pedidoId = pedidoId.toInt(),
                productoId = it.productoId,
                cantidad = it.cantidad
            )
        }

        // Insertar todos los detalles y las referencias cruzadas
        detallePedidoDao.insertAllDetalles(detallesConId)
        detallePedidoDao.insertAllCrossRefs(crossRefs)
    }

    suspend fun update(pedido: Pedido) {
        pedidoDao.update(pedido)
    }

    suspend fun delete(pedido: Pedido) {
        pedidoDao.delete(pedido)
    }
}