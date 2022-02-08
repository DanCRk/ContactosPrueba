package com.prueba.contactos.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.prueba.contactos.R
import com.prueba.contactos.databinding.FragmentDialogDeleteBinding

class DeleteDialog (
    private val onSubmitClickListener:(Boolean) -> Unit
) : DialogFragment(){

    private var _binding: FragmentDialogDeleteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Configurar la vista del dialogo
        _binding = FragmentDialogDeleteBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawableResource(R.color.transparent)

        // boton para cancelar
        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        // pasarle el true a la funcion para avisar que si se quiere borrar el contacto y cerrar el dialogo
        binding.deleteButton.setOnClickListener {
            onSubmitClickListener.invoke(true)

            dismiss()
        }

        return binding.root
    }

}