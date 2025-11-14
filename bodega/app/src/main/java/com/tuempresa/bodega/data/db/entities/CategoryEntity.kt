package com.tuempresa.bodega.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val categoryID: Int = 0,
    val name: String
)
