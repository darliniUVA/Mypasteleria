package com.example.mypasteleria.Data.Model

import com.example.mypasteleria.R

val listaProductos = listOf(

    // ------------------------
    // TORTAS CUADRADAS
    // ------------------------
    Producto(
        "TC001",
        "Tortas Cuadradas",
        "Torta Cuadrada de Chocolate",
        "Torta de chocolate con ganache y avellanas.",
        45000,
        R.drawable.torta_chocolate
    ),
    Producto(
        "TC002",
        "Tortas Cuadradas",
        "Torta Cuadrada de Frutas",
        "Bizcocho con frutas frescas y crema chantilly.",
        50000,
        R.drawable.torta_fruta
    ),
    Producto(
        "TC003",
        "Tortas Cuadradas",
        "Torta Cuadrada Especial",
        "Diseño personalizado para eventos especiales.",
        52000,
        R.drawable.torta_especial
    ),

    // ------------------------
    // TORTAS CIRCULARES
    // ------------------------
    Producto(
        "TT001",
        "Tortas Circulares",
        "Torta Circular de Vainilla",
        "Bizcocho de vainilla con crema pastelera.",
        40000,
        R.drawable.torta_de_vainilla
    ),
    Producto(
        "TT002",
        "Tortas Circulares",
        "Torta Circular de Manjar",
        "Torta tradicional chilena con manjar.",
        42000,
        R.drawable.torta_manjar
    ),
    Producto(
        "TT003",
        "Tortas Circulares",
        "Torta Circular de Boda",
        "Elegante torta de varios pisos ideal para matrimonios.",
        90000,
        R.drawable.torta_boda
    ),
    Producto(
        "TT004",
        "Tortas Circulares",
        "Tarta de Santiago",
        "Clásica tarta gallega con almendras.",
        38000,
        R.drawable.tarta_de_santiago
    ),

    // ------------------------
    // POSTRES INDIVIDUALES
    // ------------------------
    Producto(
        "PI001",
        "Postres Individuales",
        "Mousse de Chocolate",
        "Postre individual cremoso de chocolate.",
        5000,
        R.drawable.mousse_chocolate
    ),
    Producto(
        "PI002",
        "Postres Individuales",
        "Tiramisú Clásico",
        "Postre italiano con café, mascarpone y cacao.",
        5500,
        R.drawable.tiramisu_clasico
    ),
    Producto(
        "PI003",
        "Postres Individuales",
        "Empanadas de Manzana",
        "Empanaditas dulces rellenas de manzana natural.",
        3000,
        R.drawable.empanadas_manzana
    ),
    Producto(
        "PI004",
        "Postres Individuales",
        "Cheesecake Individual",
        "Porción individual del clásico cheesecake cremoso.",
        3500,
        R.drawable.cheese_cake
    ),

    // ------------------------
    // SIN AZÚCAR
    // ------------------------
    Producto(
        "PSA001",
        "Sin Azúcar",
        "Torta Sin Azúcar de Naranja",
        "Torta ligera endulzada naturalmente.",
        48000,
        R.drawable.torta_sin_azucar_de_naranja
    ),
    Producto(
        "PSA002",
        "Sin Azúcar",
        "Galletas Sin Azúcar",
        "Galletas de avena sin adición de azúcar.",
        3000,
        R.drawable.galletas_avena_veganas
    ),

    // ------------------------
    // SIN GLUTEN
    // ------------------------
    Producto(
        "PG001",
        "Sin Gluten",
        "Brownie Sin Gluten",
        "Brownie delicioso sin gluten ni harina.",
        4000,
        R.drawable.brownies_sin_gluten
    ),
    Producto(
        "PG002",
        "Sin Gluten",
        "Pan Sin Gluten",
        "Pan artesanal sin gluten.",
        2500,
        R.drawable.pan_sin_gluten
    ),

    // ------------------------
    // VEGANOS
    // ------------------------
    Producto(
        "PV001",
        "Veganos",
        "Torta Vegana de Chocolate",
        "Torta húmeda sin productos animales.",
        50000,
        R.drawable.torta_vegana_chocolate_arandanos
    ),
    Producto(
        "PV002",
        "Veganos",
        "Galletas Veganas de Avena",
        "Galletas sin lácteos y sin huevo.",
        2500,
        R.drawable.galletas_avena_veganas
    )
)
