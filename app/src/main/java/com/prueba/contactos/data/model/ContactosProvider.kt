package com.prueba.contactos.data.model

import com.prueba.contactos.R
import javax.inject.Inject

class ContactosProvider @Inject constructor() {
    val contacto = listOf(
        Contacto(
            R.drawable.contacto_1,
            nombre = "Paola",
            apellido = "Marin",
            telefono = "5613245476",
            empresa = "Google"
        ),
        Contacto(
            R.drawable.contacto_2,
            nombre = "Kat",
            apellido = "Acosta",
            telefono = "5571836782",
            empresa = "Amazon"
        ),
        Contacto(
            R.drawable.contacto_3,
            nombre = "Jose",
            apellido = "Camacho",
            telefono = "5838367846",
            empresa = ""
        ),
        Contacto(
            R.drawable.contacto_4,
            nombre = "Rosa",
            apellido = "Olvera",
            telefono = "5583572712",
            empresa = ""
        ),
        Contacto(
            R.drawable.contacto_5,
            nombre = "Grecia",
            apellido = "Anaya",
            telefono = "5942735737",
            empresa = "Oxxo"
        ),
        Contacto(
            0,
            nombre = "Elias",
            apellido = "Ruíz",
            telefono = "5973736852",
            empresa = ""
        )
    )
}