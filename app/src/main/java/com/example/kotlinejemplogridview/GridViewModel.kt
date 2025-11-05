package com.example.kotlinejemplogridview

// Clase de datos que representa cada elemento del GridView
// Usamos data class porque nos proporciona automáticamente equals(), hashCode(), toString() y copy()
data class GridViewModal(
    val itemName: String,    // Nombre del elemento a mostrar
    val itemImage: Int       // ID del recurso de la imagen (R.drawable.xxx)
)
