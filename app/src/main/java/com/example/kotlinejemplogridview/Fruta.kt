package com.example.kotlinejemplogridview

// Clase de datos que representa cada elemento (cada celda) del GridView.
// "data class" nos genera automáticamente equals(), hashCode(), toString() y copy().
data class Fruta(
    val nombre: String,   // Texto que se muestra debajo de la imagen
    val imagen: Int       // ID del recurso de la imagen (R.drawable.xxx)
)
