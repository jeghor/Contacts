package com.example.contacts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contacts.R
import com.example.contacts.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(layoutInflater)

        binding.accountButton.setOnClickListener { launchFrag() }

        binding.nameTextView.text = name
        binding.phoneTextView.text = phone

        return binding.root
    }

    private fun launchFrag(){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer,ContactInfoFragment())
            .commit()
    }

    companion object{
        private var name:String ="Name"
        private var phone:String="+375(29)-xx-xx-xxx"
        @JvmStatic
        fun setName(name_: String){
            name = name_
        }
        @JvmStatic
        fun setPhone(phone_:String){
            phone=phone_
        }
        @JvmStatic
        fun getName() = name
        @JvmStatic
        fun getPhone() = phone
    }

}