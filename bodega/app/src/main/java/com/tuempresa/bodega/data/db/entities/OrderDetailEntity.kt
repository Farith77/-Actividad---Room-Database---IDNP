package com.tuempresa.bodega.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "OrderDetail",
    primaryKeys = ["orderID", "productID"],
    foreignKeys = [
        ForeignKey(
            entity = OrderEntity::class,
            parentColumns = ["orderID"],
            childColumns = ["orderID"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["productID"],
            childColumns = ["productID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrderDetailEntity(
    val orderID: Int,
    val productID: Int,
    val quantity: Int
)
