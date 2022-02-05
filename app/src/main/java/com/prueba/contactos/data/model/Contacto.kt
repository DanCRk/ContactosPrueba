package com.prueba.contactos.data.model

import android.graphics.drawable.Drawable

// Creamos la clase contacto donde tendra los atributos que querramos que tenga un contacto

data class Contacto(
    val img:Int=0,
    val nombre:String,
    val apellido:String,
    val telefono:String,
    val empresa:String
)
