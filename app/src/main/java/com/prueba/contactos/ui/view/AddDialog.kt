package com.prueba.contactos.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.prueba.contactos.R
import com.prueba.contactos.data.model.Contacto
import com.prueba.contactos.databinding.FragmentDialogDeleteBinding

class AddDialog() : DialogFragment() {

    private var _binding: FragmentDialogDeleteBinding? = null
    private val binding get() = _binding!!
    private lateinit var contactoNuevo: Contacto
    private var validartelefono = false
    private var validarnombre = false
    private lateinit var nombre: String
    private lateinit var apellido: String
    private lateinit var empresa: String
    private lateinit var telefono: String
    private var imageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogDeleteBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawableResource(R.color.transparent)

        binding.cancelButton.setOnClickListener {
            dismiss()
        }


        binding.submitButton.setOnClickListener {
            if (!binding.addNombre.text.isNullOrEmpty()) {
                nombre = binding.addNombre.text.toString()
                validarnombre = true
            } else {
                Toast.makeText(context, "Nombre invalido", Toast.LENGTH_SHORT).show()
            }

            apellido = if (!binding.addApellido.text.isNullOrEmpty()) {
                binding.addApellido.text.toString()
            } else {
                ""
            }

            if (!binding.addTelefono.text.isNullOrEmpty()) {
                validartelefono = true
                telefono = binding.addTelefono.text.toString()
            } else {
                Toast.makeText(context, "Telefono invalido", Toast.LENGTH_SHORT).show()
            }

            empresa = if (!binding.addEmpresa.text.isNullOrEmpty()) {
                binding.addEmpresa.text.toString()
            } else {
                ""
            }

            if (validartelefono && validarnombre) {
                contactoNuevo = Contacto(0, nombre, apellido, telefono, empresa)
                setAllEmpty()
                dismiss()
            }
        }

        binding.addFoto.setOnClickListener {
            loadImageFromGalry()
        }

        binding.addImage.setOnClickListener {
            loadImageFromGalry()
        }

        return binding.root
    }

    private fun setAllEmpty() {
        binding.addNombre.text = null
        binding.addApellido.text = null
        binding.addTelefono.text = null
        binding.addEmpresa.text = null
        binding.containerAddFoto.visibility = ViewGroup.VISIBLE
        binding.addImage.visibility = ViewGroup.GONE
    }

    @SuppressLint("IntentReset")
    fun loadImageFromGalry() {
        val intent = Intent(
            Intent.ACTION_PICK
        )
        intent.type = "image/*"
        this.startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            imageUri = data!!.data
            Glide.with(this)
                .load(imageUri)
                .transform(CenterCrop())
                .into(binding.addImage)
            binding.addImage.visibility = ViewGroup.VISIBLE
            binding.containerAddFoto.visibility = ViewGroup.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        validartelefono = false
        validarnombre = false
        _binding = null
    }

}