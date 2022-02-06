package com.prueba.contactos.data.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.prueba.contactos.R
import com.prueba.contactos.data.model.Contacto
import com.prueba.contactos.databinding.ItemContactoBinding

class ViewHolder (view: View): RecyclerView.ViewHolder(view){

    //declaramos la variable binding para acceder a todos los elementos de nuestro item contacto
    private val binding =ItemContactoBinding.bind(view)

    fun bind(contacto: Contacto, onClickListener:(Contacto)->Unit){

        val nombreCompleto = contacto.nombre + " " + contacto.apellido

        //cargamos la imagen en la vista

        if (contacto.img==(0)){
            binding.imgContactoList.setImageResource(R.drawable.ic_usuario)
        }else{
            binding.imgContactoList.setImageResource(contacto.img)
        }

        //cargamos el nombre del contacto
        binding.nombreContactoList.text = nombreCompleto

        // le pasamos el objeto a nuestra funcion listener
        itemView.setOnClickListener {
            onClickListener(contacto)
        }

    }

}