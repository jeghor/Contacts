package com.example.contacts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contacts.R
import com.example.contacts.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        setListFrag()
        binding.addContact.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer,AddContactFragment())
                .commit()

        }
        return binding.root
    }

    private fun setListFrag(){
        if (ContactsFragment.getName() == "Name" && ContactsFragment.getPhone()=="+375(29)-xx-xx-xxx"){
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.cont_in_frag,NoContactsFragment())
                .commit()
        } else{
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.cont_in_frag,ContactsFragment())
                .commit()
        }
    }
}
