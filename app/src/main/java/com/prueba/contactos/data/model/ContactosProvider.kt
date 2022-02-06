package com.prueba.contactos.data.model

import com.prueba.contactos.R
import javax.inject.Inject

class ContactosProvider @Inject constructor() {
    var contactos = mutableListOf<Contacto>(
        Contacto(
            R.drawable.contacto_1,
            null,
            nombre = "Paola",
            apellido = "Marin",
            telefono = "5613245476",
            empresa = "Google"
        ),
        Contacto(
            R.drawable.contacto_2,
            null,
            nombre = "Kat",
            apellido = "Acosta",
            telefono = "5571836782",
            empresa = "Amazon"
        ),
        Contacto(
            R.drawable.contacto_3,
            null,
            nombre = "Daniel",
            apellido = "Avila",
            telefono = "5838367846",
            empresa = "Smart Scale"
        ),
        Contacto(
            R.drawable.contacto_4,
            null,
            nombre = "Rosa",
            apellido = "Olvera",
            telefono = "5583572712",
            empresa = ""
        ),
        Contacto(
            R.drawable.contacto_5,
            null,
            nombre = "Grecia",
            apellido = "Anaya",
            telefono = "5942735737",
            empresa = "Oxxo"
        ),
        Contacto(
            R.drawable.ic_usuario,
            null,
            nombre = "Elias",
            apellido = "Ruíz",
            telefono = "5973736852",
            empresa = ""
        )
    )

    fun addContact(contacto: Contacto){
        contactos.add(contacto)
    }

    fun removeContact(contacto: Contacto){
        contactos.remove(contacto)
    }
}