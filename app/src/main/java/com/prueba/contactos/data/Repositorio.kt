package com.prueba.contactos.data

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

    // Eliminar un contacto y devolver la lista con todos los contactos restantes
    fun deleteContact(contacto: Contacto): List<Contacto> {
        provider.removeContact(contacto)
        response = provider.contactos
        return response
    }

    // Agregar un contacto a la lista y devolver la lista con todos los contactos nuevos
    fun addContact(contacto: Contacto):List<Contacto>{
        provider.addContact(contacto)
        response= provider.contactos
        return response
    }
}