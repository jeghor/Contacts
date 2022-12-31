package com.example.contacts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.AccountsAdapter
import com.example.contacts.App
import com.example.contacts.R
import com.example.contacts.databinding.FragmentRecycleBinding
import com.example.contacts.models.Account
import com.example.contacts.models.AccountListener
import com.example.contacts.models.AccountService

class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentRecycleBinding
    private lateinit var adapter: AccountsAdapter

    private val accountService: AccountService = AccountService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecycleBinding.inflate(layoutInflater)
        adapter = AccountsAdapter(object : AccountsAdapter.Listener{
            override fun onContact(account: Account) {
                val cif = ContactInfoFragment()
                val bundle = Bundle()
                bundle.putString("id",account.id)
                cif.arguments = bundle
                parentFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer,cif)
                    .commit()
            }

            override fun onCallContact(account: Account) {
                Toast.makeText(activity,"call not able",Toast.LENGTH_SHORT).show()
            }

        })
        App.accountService.addListener(accountListener)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.fragmentRecycle.layoutManager = layoutManager
        binding.fragmentRecycle.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        accountService.removeListener(accountListener)
    }


    private var accountListener: AccountListener = {
        adapter.accounts = it
    }
}