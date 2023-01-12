package com.example.contacts.fragments

import android.os.Bundle
import android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.contacts.App
import com.example.contacts.R
import com.example.contacts.databinding.FragmentAddContactBinding


class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding
    private var viewType = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(layoutInflater)

        binding.backArrow.setOnClickListener{goBack()}
        binding.doneContact.setOnClickListener{addContact()}

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            with(binding){
                when (checkedId) {
                    PhoneRadioButton.id -> {
                        contactPropertyText.setText(R.string.phone_number)
                        editTextPhone.setHint(R.string.number)
                    }
                    else -> {
                        contactPropertyText.setText(R.string.email)
                        editTextPhone.inputType = TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        editTextPhone.setHint(R.string.email_example)
                        viewType = 1
                    }
                }
            }

        }

        return binding.root
    }

    private fun goBack(){
        findNavController().popBackStack()
    }


    private fun addContact(){
        val bool = App.entryFieldService.checkField(
            requireActivity(),binding.editTextTextPersonName,binding.editTextPhone
        )
        if (bool){
            val name = binding.editTextTextPersonName.text.toString()
            if (viewType == 0){
                val phone = binding.editTextPhone.text.toString()
                App.accountService.addAccount(name,phone,"none",viewType)
            } else{
                val email = binding.editTextPhone.text.toString()
                App.accountService.addAccount(name,"none",email,viewType)
            }
            goBack()
        }
    }
}