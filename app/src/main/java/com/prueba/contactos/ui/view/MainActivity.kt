package com.prueba.contactos.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.prueba.contactos.data.adapters.RecyclerContactosAdapter
import com.prueba.contactos.data.model.Contacto
import androidx.activity.viewModels
import com.prueba.contactos.R
import com.prueba.contactos.databinding.ActivityMainBinding
import com.prueba.contactos.ui.viewmodel.ContactosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val contactosViewModel: ContactosViewModel by viewModels()
    private lateinit var adapter: RecyclerContactosAdapter
    private val contactosList = mutableListOf<Contacto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Settear la vista en el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Llamamos a la funcion onCrate del ViewModel
        contactosViewModel.onCreate()

        setRecyclerView()

        contactosViewModel.allContacts.observe(this, {list ->
            if (!list.isNullOrEmpty()){
                for (ct in list){
                    if (!contactosList.contains(ct)){
                        contactosList.add(ct)
                    }
                }
                refreshRecyclerView()
            }
        })
    }

    // Establecer los parametros y el adaptador del recyvlerview
    private fun setRecyclerView() {
        adapter = RecyclerContactosAdapter(contactosList) { contacto -> onItemSelected(contacto) }
        binding.rvcontactos.layoutManager = LinearLayoutManager(this)
        binding.rvcontactos.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    // Funcion cuando se hace click en un contacto
    private fun onItemSelected(contacto: Contacto) {
        val nombreCompleto = contacto.nombre + " " + contacto.apellido
        Toast.makeText(this, nombreCompleto, Toast.LENGTH_SHORT).show()
    }

    // Refrescar el recyclerview
    private fun refreshRecyclerView(){ if(!contactosList.isNullOrEmpty()){ adapter.notifyDataSetChanged() }else{ showError() } }

    // Mostrar un error con un toast
    private fun showError(){ Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show() }
}