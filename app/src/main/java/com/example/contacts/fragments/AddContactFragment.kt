package com.example.contacts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contacts.databinding.FragmentAddContactBinding
import com.example.contacts.App

class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(layoutInflater)

        binding.backArrow.setOnClickListener{goBack()}
        binding.doneContact.setOnClickListener{addContact()}

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