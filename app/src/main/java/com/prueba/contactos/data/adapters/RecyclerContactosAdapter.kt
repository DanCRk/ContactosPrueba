package com.prueba.contactos.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prueba.contactos.R
import com.prueba.contactos.data.model.Contacto

class RecyclerContactosAdapter (private val contactosList:List<Contacto>, private val onClickListener:(Contacto) -> Unit): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // le pasamos la vista del item contacto
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_contacto, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // llamamos a la funcion bind del holder y le pasamos un contacto y la funcion onClickListener
        holder.bind(contactosList[position], onClickListener)
    }

    // Le pasamos el tama ño de la lista
    override fun getItemCount(): Int = contactosList.size


}