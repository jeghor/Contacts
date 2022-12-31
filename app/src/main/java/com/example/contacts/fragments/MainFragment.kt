package com.example.contacts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contacts.App
import com.example.contacts.R
import com.example.contacts.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(layoutInflater)

        if (App.accountService.accounts.size!=0){
            binding.noContactsText.visibility = View.GONE
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.cont_in_frag,ContactsFragment())
                .commit()
        }

        binding.addContact.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer,AddContactFragment())
                .commit()
        }
        return binding.root
    }
}
