package com.prueba.contactos

import com.prueba.contactos.data.model.Contacto
import com.prueba.contactos.data.model.ContactosProvider
import javax.inject.Inject

class Repositorio @Inject constructor(private val provider:ContactosProvider){

    lateinit var response: List<Contacto>

    // Recuperar todos los contactos del provider
    fun getAllContacts(): List<Contacto> {
        response= provider.contactos
        return response
    }

    fun deleteContact(contacto: Contacto): List<Contacto> {
        provider.removeContact(contacto)
        response = provider.contactos
        return response
    }
}