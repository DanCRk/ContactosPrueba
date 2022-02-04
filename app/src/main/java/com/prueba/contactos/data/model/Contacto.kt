package com.prueba.contactos.data.model

import android.graphics.drawable.Drawable

// Creamos la clase contacto donde tendra los atributos que querramos que tenga un contacto

data class Contacto(
    val img:Drawable,
    val nombre:String,
    val telefono:String,
    val empresa:String
)
