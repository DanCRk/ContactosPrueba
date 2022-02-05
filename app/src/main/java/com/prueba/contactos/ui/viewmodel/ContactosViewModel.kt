package com.prueba.contactos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prueba.contactos.data.model.Contacto
import com.prueba.contactos.domain.GetAllContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactosViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase
):ViewModel(){
    // establecer la variable de tipo live data que contendra nuestra lista de contactos
    val allContacts = MutableLiveData<List<Contacto>>()

    // Funcion que se llamara al momento de iniciar la app
    fun onCreate(){
        val result = getAllContactsUseCase()
        // Verificamos que la lista no sea nula ni vacia
        if (!result.isNullOrEmpty()){
            // le asignamos la lista a nuestro live data
            allContacts.postValue(result)
        }
    }
}