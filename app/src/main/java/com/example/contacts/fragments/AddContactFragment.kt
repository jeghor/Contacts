package com.example.contacts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contacts.App
import com.example.contacts.R
import com.example.contacts.databinding.FragmentAddContactBinding


class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding

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
                        editTextPhone.setHint(R.string.email_example)
                    }
                }
            }

        }

        return binding.root
    }

    private fun goBack(){
        requireActivity().onBackPressed()
    }


    private fun addContact(){
        val bool = App.entryFieldService.checkField(
            requireActivity(),binding.editTextTextPersonName,binding.editTextPhone
        )
        if (bool){
            val name = binding.editTextTextPersonName.text.toString()
            val phone = binding.editTextPhone.text.toString()
            App.accountService.addAccount(name,phone)
            goBack()
        }
    }
}