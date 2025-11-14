package com.tuempresa.bodega.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "OrderEntity",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["customerID"],
            childColumns = ["customerID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val orderID: Int = 0,
    val customerID: Int,
    val orderDate: Long   // timestamp
)
