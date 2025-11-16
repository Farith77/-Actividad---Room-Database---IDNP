package com.bodega.delivery.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.bodega.delivery.data.entities.DetallePedido
import com.bodega.delivery.data.entities.ProductoPedidoCrossRef

@Dao
interface DetallePedidoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetalle(detalle: DetallePedido)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: ProductoPedidoCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetalles(detalles: List<DetallePedido>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCrossRefs(crossRefs: List<ProductoPedidoCrossRef>)
}