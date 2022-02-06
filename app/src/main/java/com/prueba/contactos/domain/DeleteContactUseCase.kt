package com.prueba.contactos.domain

import com.prueba.contactos.Repositorio
import com.prueba.contactos.data.model.Contacto
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(private val repositorio: Repositorio){

    // establecemos el caso de uso donde borramos el contacto seleccionado
    operator fun invoke(contacto: Contacto): List<Contacto> {
        return repositorio.deleteContact(contacto)
    }

}