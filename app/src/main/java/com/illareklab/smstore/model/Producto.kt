package com.illareklab.smstore.model

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: String,
    val categoria: String,
    val favorito: Boolean = false
)

// Datos de prueba para la lista
val productosDePrueba = listOf(
    Producto(1, "Café Premium 500g", "S/ 45.00", "Bebidas", true),
    Producto(2, "Chocolate artesanal 70%", "S/ 28.00", "Dulces"),
    Producto(3, "Pan de masa madre", "S/ 18.00", "Panadería"),
    Producto(4, "Miel de abeja orgánica", "S/ 35.00", "Endulzantes", true),
    Producto(5, "Mermelada de fresa", "S/ 22.00", "Conservas"),
    Producto(6, "Té verde matcha", "S/ 55.00", "Bebidas"),
    Producto(7, "Galletas de avena", "S/ 15.00", "Dulces"),
    Producto(8, "Aceite de oliva extra virgen", "S/ 68.00", "Aceites")
)