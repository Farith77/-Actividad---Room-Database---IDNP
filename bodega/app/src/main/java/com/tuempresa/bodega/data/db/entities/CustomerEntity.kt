package com.tuempresa.bodega.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) val customerID: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String
)
