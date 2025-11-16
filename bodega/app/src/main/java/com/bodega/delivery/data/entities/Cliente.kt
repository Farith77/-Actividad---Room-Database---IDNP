package com.bodega.delivery.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val direccion: String,
    val email: String,
    val fechaRegistro: Long
)