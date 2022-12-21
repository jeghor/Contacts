package com.example.contacts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contacts.databinding.FragmentAddContactBinding
import android.widget.Toast

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
        val bool = checkField()
        if (bool){
            ContactsFragment.setName(binding.editTextTextPersonName.text.toString())
            ContactsFragment.setPhone(binding.editTextPhone.text.toString())
            goBack()
        }
    }

    private fun checkField(): Boolean{
        var field = false
        if ("${binding.editTextPhone.text}"!= ""&& "${binding.editTextTextPersonName.text}"!= ""){
            field = true
        } else if ("${binding.editTextPhone.text}"== ""&& "${binding.editTextTextPersonName.text}"== ""){
            Toast.makeText(activity,"Please, enter name and phone number",Toast.LENGTH_SHORT).show()
        } else if ("${binding.editTextPhone.text}"== ""){
            Toast.makeText(activity,"Please, enter phone number",Toast.LENGTH_SHORT).show()
        } else if ("${binding.editTextTextPersonName.text}"== ""){
            Toast.makeText(activity,"Please, enter name",Toast.LENGTH_SHORT).show()
        }
        return field
    }
}