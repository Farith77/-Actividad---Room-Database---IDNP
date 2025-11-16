package com.bodega.delivery.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["pedidoId", "productoId"])
data class ProductoPedidoCrossRef(
    val pedidoId: Int,
    val productoId: Int,
    val cantidad: Int
)