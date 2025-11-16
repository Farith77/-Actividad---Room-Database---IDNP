package com.bodega.delivery.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bodega.delivery.data.entities.Cliente
import com.bodega.delivery.data.entities.Pedido

data class ClienteConPedidos(
    @Embedded val cliente: Cliente,
    @Relation(
        parentColumn = "id",
        entityColumn = "clienteId"
    )
    val pedidos: List<Pedido>
)