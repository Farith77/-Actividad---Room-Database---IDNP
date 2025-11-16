package com.bodega.delivery.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bodega.delivery.data.entities.Categoria
import com.bodega.delivery.data.entities.Producto

data class ProductoConCategorias(
    @Embedded val producto: Producto,
    @Relation(
        parentColumn = "categoriaId",
        entityColumn = "id"
    )
    val categoria: Categoria
)