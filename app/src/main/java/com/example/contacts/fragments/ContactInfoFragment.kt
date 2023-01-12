package com.example.contacts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.contacts.App
import com.example.contacts.R
import com.example.contacts.databinding.FragmentContactInfoBinding

class ContactInfoFragment : Fragment() {

    private lateinit var binding: FragmentContactInfoBinding
    private lateinit var accId:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bundle = arguments
        val accountId = bundle?.getString("id").toString()
        accId = accountId

        binding = FragmentContactInfoBinding.inflate(layoutInflater)
        binding.backArrow.setOnClickListener{goBack()}
        binding.doneContact.setOnClickListener{changeContact()}
        binding.deleteFab.setOnClickListener { deleteContact() }

        App.accountService.accounts.forEach {
            if (it.id == accountId){
                binding.toolbar.title = it.name
                binding.editTextTextPersonName.setText(it.name)
                binding.editTextPhone.setText(it.phone)
                if (it.account_type==1){
                    binding.textView3.setText(R.string.email)
                    binding.editTextPhone.setText(it.email)
                }
            }
        }

        return binding.root
    }

    private fun deleteContact(){
        findNavController().navigate(R.id.action_contactInfoFragment_to_popUpFragment2,
                bundleOf("id" to accId)
            )
        /*val puf = PopUpFragment()
        val bundle = Bundle()
        bundle.putString("id",accId)
        puf.arguments = bundle
        puf.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp") // show FragmentDialog*/
    }

    private fun goBack(){
        findNavController().popBackStack()
    }

    private fun changeContact(){
        val bool = App.entryFieldService.checkField(
            requireActivity(),binding.editTextTextPersonName,binding.editTextPhone
        )
        if (bool){
            with(App.accountService){
                accounts.forEach {
                    if (it.id == accId){
                        it.name = binding.editTextTextPersonName.text.toString()
                        it.phone = binding.editTextPhone.text.toString()
                        binding.toolbar.title = binding.editTextTextPersonName.text.toString()
                    }
                }
            }
            Toast.makeText(activity,"contact was successfully changed",Toast.LENGTH_SHORT).show()
        }
    }
}