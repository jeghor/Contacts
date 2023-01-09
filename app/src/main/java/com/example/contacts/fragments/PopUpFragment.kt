package com.example.contacts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.contacts.App
import com.example.contacts.databinding.FragmentPopUpBinding
import com.example.contacts.models.Account

class PopUpFragment : DialogFragment() {

    private lateinit var binding: FragmentPopUpBinding
    private lateinit var accId:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bundle = arguments
        val accountId = bundle?.getString("id").toString()
        accId = accountId

        binding = FragmentPopUpBinding.inflate(layoutInflater)
        binding.okBtn.setOnClickListener {
            deleteContact()
            requireDialog().onBackPressed()
            requireActivity().onBackPressed()
        }
        binding.cancelBtn.setOnClickListener { requireDialog().onBackPressed() }

        return binding.root
    }

    private fun deleteContact(){
        with(App.accountService){
            var contactToDelete: Account? = null
            accounts.forEach {
                if (it.id == accId){
                    contactToDelete = it
                }
            }
            contactToDelete?.let { deleteAccount(it) }
        }
    }
}