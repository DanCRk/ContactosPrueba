package com.prueba.contactos.domain

import com.prueba.contactos.data.Repositorio
import com.prueba.contactos.data.model.Contacto
import javax.inject.Inject

class AddContactUseCase @Inject constructor(private val repositorio: Repositorio){

    // establecemos el caso de uso donde añadimos el contacto seleccionado
    operator fun invoke(contacto: Contacto): List<Contacto> {
        return repositorio.addContact(contacto)
    }
}