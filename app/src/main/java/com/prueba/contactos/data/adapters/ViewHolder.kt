package com.prueba.contactos.data.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prueba.contactos.data.model.Contacto
import com.prueba.contactos.databinding.ItemContactoBinding

class ViewHolder (val view: View): RecyclerView.ViewHolder(view){

    //declaramos la variable binding para acceder a todos los elementos de nuestro item contacto
    private val binding =ItemContactoBinding.bind(view)

    fun bind(contacto: Contacto, onClickListener:(Contacto)->Unit){
        //cargamos la imagen en la vista
        binding.imgContactoList.setImageResource(contacto.img)

        //cargamos el nombre del contacto
        binding.nombreContactoList.text = contacto.nombre

        // le pasamos el objeto a nuestra funcion listener
        itemView.setOnClickListener {
            onClickListener(contacto)
        }

    }

}