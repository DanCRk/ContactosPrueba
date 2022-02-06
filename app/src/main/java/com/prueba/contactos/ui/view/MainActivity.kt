package com.prueba.contactos.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
    private var isvisible: Boolean = false
    var dialog = AddDialog()
    lateinit var contactoSelected: Contacto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Settear la vista en el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Llamamos a la funcion onCrate del ViewModel
        contactosViewModel.onCreate()

        setRecyclerView()

        // Observar la variable live data que tenemos para cuando se obtenga la lista de cotnactos se la pasamos al adaptador
        contactosViewModel.allContacts.observe(this, { list ->
            for (ct in list) {
                if (!contactosList.contains(ct)) {
                    contactosList.add(ct)
                }
            }
            refreshRecyclerView()
        })

        contactosViewModel.contactsDeleted.observe(this, { list ->
            contactosList.clear()
            for (ct in list) {
                if (!contactosList.contains(ct)) {
                    contactosList.add(ct)
                }
            }
            refreshRecyclerView()
        })

        binding.borrarContacto?.setOnClickListener {
            contactosViewModel.deleteContact(contactoSelected)
            isvisible=false
            binding.details?.visibility = ViewGroup.GONE
        }

        addContact()
    }

    fun addContact(){
        binding.addcontactos?.setOnClickListener {
            dialog.isCancelable = false
            dialog.show(supportFragmentManager ,"addDialog")
        }
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

        contactoSelected = contacto
        contactDetails()
        //Comprobar si el layout de detalles es visible, si no es asi lo muestras
        if (!isvisible) {
            binding.details?.visibility = ViewGroup.VISIBLE
            isvisible = true
        }
    }

    //Funcion para establecer el detalle del contacto
    private fun contactDetails() {
        val nombreCompleto = contactoSelected.nombre + " " + contactoSelected.apellido
        binding.nombre.text = nombreCompleto
        binding.telefono.text = contactoSelected.telefono
        if (contactoSelected.empresa != "") {
            binding.empresa.text = contactoSelected.empresa
        } else {
            binding.empresa.text = getString(R.string.sin_empresa)
        }
        if (contactoSelected.img == 0) {
            binding.imgContacto.setImageResource(R.drawable.ic_usuario)
        } else {
            binding.imgContacto.setImageResource(contactoSelected.img)
        }
    }

    // Refrescar el recyclerview
    private fun refreshRecyclerView() {
        if (!contactosList.isNullOrEmpty()) {
            adapter.notifyDataSetChanged()
        } else {
            showError()
        }
    }

    // Mostrar un error con un toast
    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}