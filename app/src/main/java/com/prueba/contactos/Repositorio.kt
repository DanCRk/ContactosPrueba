package com.prueba.contactos

import com.prueba.contactos.data.model.Contacto
import com.prueba.contactos.data.model.ContactosProvider
import javax.inject.Inject

class Repositorio @Inject constructor(private val provider:ContactosProvider){

    // Recuperar todos los contactos del provider
    fun getAllContacts():List<Contacto>{
        val response = provider.contacto
        return response
    }
}