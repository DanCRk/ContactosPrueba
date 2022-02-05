package com.prueba.contactos.data.model

import com.prueba.contactos.R
import javax.inject.Inject

class ContactosProvider @Inject constructor() {
    val contacto = listOf<Contacto>(
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
            telefono = "5613245476",
            empresa = "Google"
        ),
        Contacto(
            R.drawable.contacto_3,
            nombre = "Jose",
            apellido = "Anaya",
            telefono = "5613245476",
            empresa = "Google"
        ),
        Contacto(
            R.drawable.contacto_4,
            nombre = "Rosa",
            apellido = "Olvera",
            telefono = "5613245476",
            empresa = "Google"
        ),
        Contacto(
            R.drawable.contacto_5,
            nombre = "Grecia",
            apellido = "Camacho",
            telefono = "5613245476",
            empresa = "Google"
        ),
    )
}