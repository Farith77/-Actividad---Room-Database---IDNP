package com.bodega.delivery.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bodega.delivery.data.entities.Pedido
import com.bodega.delivery.data.entities.Producto
import com.bodega.delivery.data.entities.ProductoPedidoCrossRef

data class PedidoConDetalles(
    @Embedded val pedido: Pedido,
    @Relation(
        parentColumn = "id",
        entity = Producto::class,
        entityColumn = "id",
        associateBy = Junction(
            value = ProductoPedidoCrossRef::class,
            parentColumn = "pedidoId",
            entityColumn = "productoId"
        )
    )
    val productos: List<Producto>
)