package com.example.contacts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R
import com.example.contacts.databinding.FragmentContactInfoBinding

class ContactInfoFragment : Fragment() {

    private lateinit var binding: FragmentContactInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactInfoBinding.inflate(layoutInflater)
        binding.backArrow.setOnClickListener{goBack()}
        binding.doneContact.setOnClickListener{changeContact()}
        binding.deleteFab.setOnClickListener { deleteContact() }

        binding.toolbar.title = ContactsFragment.getName()
        binding.editTextTextPersonName.setText(ContactsFragment.getName())
        binding.editTextPhone.setText(ContactsFragment.getPhone())

        return binding.root
    }

    private fun deleteContact(){
        PopUpFragment().show((activity as AppCompatActivity).supportFragmentManager,"showPopUp") // show FragmentDialog
    }

    private fun goBack(){
        requireActivity().onBackPressed()
    }

    private fun changeContact(){
        val bool = checkField()
        if (bool){
            ContactsFragment.setName(binding.editTextTextPersonName.text.toString())
            ContactsFragment.setPhone(binding.editTextPhone.text.toString())
            Toast.makeText(activity,"contact was successfully changed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkField(): Boolean{
        var field = false
        if ("${binding.editTextPhone.text}"!= ""&& "${binding.editTextTextPersonName.text}"!= ""){
            field = true
        } else if ("${binding.editTextPhone.text}"== ""&& "${binding.editTextTextPersonName.text}"== ""){
            Toast.makeText(activity,"Please, enter name and phone number", Toast.LENGTH_SHORT).show()
        } else if ("${binding.editTextPhone.text}"== ""){
            Toast.makeText(activity,"Please, enter phone number", Toast.LENGTH_SHORT).show()
        } else if ("${binding.editTextTextPersonName.text}"== ""){
            Toast.makeText(activity,"Please, enter name", Toast.LENGTH_SHORT).show()
        }
        return field
    }
}