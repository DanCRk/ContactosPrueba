package com.prueba.contactos.domain

import com.prueba.contactos.Repositorio
import com.prueba.contactos.data.model.Contacto
import javax.inject.Inject

class GetAllContactsUseCase @Inject constructor(private val repositorio: Repositorio){

    // establecemos el caso de uso donde recuperamos todos los contactos
    operator fun invoke(): List<Contacto> {
        return repositorio.getAllContacts()
    }
}