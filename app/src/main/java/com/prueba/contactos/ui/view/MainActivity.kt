package com.prueba.contactos.ui.view

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.prueba.contactos.R
import com.prueba.contactos.data.adapters.RecyclerContactosAdapter
import com.prueba.contactos.data.model.Contacto
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

        // observar si hay algun cambioo en el viewmodel que nos diga si debemos borrar un elemento de la lista
        contactosViewModel.contactDeleted.observe(this, { ct ->
            contactosList.remove(ct)
            refreshRecyclerView()
            Toast.makeText(this, "Contacto borrado", Toast.LENGTH_SHORT).show()
        })

        // comprobar si hay un nuevo contacto y si lo hay
        contactosViewModel.addContacts.observe(this, { list ->
            contactosList.add(list.last())
            refreshRecyclerView()
            Toast.makeText(this, "Contacto añadido", Toast.LENGTH_SHORT).show()
        })

        // listener del boton para borraer el contacto, se le pasa el que esta en detalles para borrarlo del repositorio
        binding.borrarContacto.setOnClickListener {
            contactosViewModel.deleteContact(contactoSelected)
            isvisible = false
            binding.details.visibility = ViewGroup.GONE
        }

        addContactDialogFragment()
    }

    fun addContactDialogFragment() {
        //llamamos al dialog framgent y recibimos el contacto introducido por el usuario
        binding.addcontactos?.setOnClickListener {
            AddDialog(
                onSubmitClickListener = { contacto ->
                    contactosViewModel.addContact(contacto)
                }
            ).show(supportFragmentManager, "addDialogFragment")
        }
    }

    // Establecer los parametros y el adaptador del recyvlerview
    private fun setRecyclerView() {
        adapter = RecyclerContactosAdapter(contactosList) { contacto -> onItemSelected(contacto) }
        binding.rvcontactos.layoutManager = LinearLayoutManager(this)
        binding.rvcontactos.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    // Funcion cuando se hace click en un contacto de la lista se lo mandamos a detalles y hacemos visible el layout con los detalles
    private fun onItemSelected(contacto: Contacto) {
        contactoSelected = contacto
        contactDetails()
        //Comprobar si el layout de detalles es visible, si no es asi lo muestras
        if (!isvisible) {
            binding.details?.visibility = ViewGroup.VISIBLE
            isvisible = true
        }
    }

    //Funcion para establecer el detalle del contacto en la vista
    private fun contactDetails() {
        val nombreCompleto = contactoSelected.nombre + " " + contactoSelected.apellido
        binding.nombre.text = nombreCompleto
        binding.telefono.text = contactoSelected.telefono
        if (contactoSelected.empresa != "") {
            binding.empresa.text = contactoSelected.empresa
        } else {
            binding.empresa.text = getString(R.string.sin_empresa)
        }
        if (!contactoSelected.img.equals(0)){
            Glide.with(this)
                .load(contactoSelected.img)
                .into(binding.imgContacto)
        }else{
            Glide.with(this)
                .asDrawable()
                .load(contactoSelected.img2)
                .into(binding.imgContacto)
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
        Toast.makeText(this, "Sin contactos", Toast.LENGTH_SHORT).show()
    }
}